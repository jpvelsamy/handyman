package in.handyman.raven.lib.agadia.eocsplitting;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import in.handyman.raven.exception.HandymanException;
import in.handyman.raven.lambda.doa.audit.ActionExecutionAudit;
import in.handyman.raven.lib.model.EpisodeOfCoverage;
import in.handyman.raven.util.CommonQueryUtil;
import in.handyman.raven.util.InstanceUtil;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import okhttp3.*;
import org.jdbi.v3.core.Jdbi;
import org.slf4j.Logger;
import org.slf4j.Marker;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.*;
import java.util.stream.Collectors;

public class QrCodeCoverage {

    private final Logger log;

    private final EpisodeOfCoverage episodeOfCoverage;

    private final Marker aMarker;

    private final ActionExecutionAudit action;

    public QrCodeCoverage(Logger log, EpisodeOfCoverage episodeOfCoverage, Marker aMarker, ActionExecutionAudit action) {
        this.log = log;
        this.episodeOfCoverage = episodeOfCoverage;
        this.aMarker = aMarker;
        this.action = action;
    }

    public Map<String, List<Integer>> splitByQrcode(Jdbi jdbi,String sorItem){
        Map<String,List<Integer>> qrObjectMap = new HashMap<>();
        List<Map<String, Object>> eocGroupingQrItemRequestInfos= queryExecutor(jdbi,sorItem,episodeOfCoverage.getFilepath());
        final ObjectMapper mapper = new ObjectMapper();
        final String URI = action.getContext().get("copro.qr-attribution.url");
        final MediaType MediaTypeJSON = MediaType
                .parse("application/json; charset=utf-8");

        for (var eocGroupingQrItemRequestInfo : eocGroupingQrItemRequestInfos){

            final OkHttpClient httpclient = InstanceUtil.createOkHttpClient();
            final String inputFilePathString= Optional.ofNullable(eocGroupingQrItemRequestInfo.get("file_path")).map(String::valueOf).orElse("");
            final String pageNoString= Optional.ofNullable(eocGroupingQrItemRequestInfo.get("paper_no")).map(String::valueOf).orElse("");
            int pageNoInt=Integer.parseInt(pageNoString);

            log.info(aMarker, " Input variables id : {}", action.getActionId());
            final ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put("inputFilePath", inputFilePathString);
            log.info(aMarker, " Input variables id : {}", action.getActionId());
            Request request = new Request.Builder().url(URI)
                    .post(RequestBody.create(objectNode.toString(), MediaTypeJSON)).build();
            String name = episodeOfCoverage.getName();
            log.debug(aMarker, "Request has been build with the parameters \n URI : {} \n Input-File-Path : {} \n", URI,inputFilePathString );
            log.info(aMarker, "The Request Details : {}", request);

            try (Response response = httpclient.newCall(request).execute()) {
                String responseBody = response.body().string();
                //This map for store filepath and qr output

                if (response.isSuccessful()) {
                    log.info(aMarker, "The Successful Response for {} --> {}", name, responseBody);

                    QrReader qrResponse=mapper.readValue(responseBody, QrReader.class);
                    if(!qrResponse.getQr().isEmpty()){
                        if(qrObjectMap.containsKey(qrResponse.getQr().get(0))){
                            List<Integer> list=qrObjectMap.get(qrResponse.getQr().get(0));
                            list.add(pageNoInt);
                            qrObjectMap.put(qrResponse.getQr().get(0),list);
                        }else {
                            List<Integer> list=new ArrayList<>();
                            list.add(pageNoInt);
                            qrObjectMap.put(qrResponse.getQr().get(0),list);
                        }
                    }

                    log.info(aMarker, "The successed output for eoc splitting with QR code {} --> {}", name, qrObjectMap);
                } else {
                    log.info(aMarker, "The Failure Response {} --> {}", name, responseBody);
                    action.getContext().put(name.concat(".error"), "true");
                    action.getContext().put(name.concat(".errorMessage"), responseBody);
                }

                action.getContext().put(name + ".isSuccessful", String.valueOf(response.isSuccessful()));
            } catch (Exception e) {
                log.error(aMarker, "The Exception occurred for group by qr code ", e);
                action.getContext().put(name + ".isSuccessful", "false");
                throw new HandymanException("Failed to execute", e);
            }
            log.info(aMarker, "<-------Episode of coverage Action for {} with group by qr-code has completed------->" + episodeOfCoverage.getName());
        }

        return qrObjectMap;
    }
    public List<Map<String, Object>> queryExecutor(Jdbi jdbi, String sorItem, String unFormattedQueryString){
        final List<Map<String, Object>> requestInfos = new ArrayList<>();
        try{
            jdbi.useTransaction(handle -> {
                final List<String> formattedQuery = CommonQueryUtil.getFormattedQuery(unFormattedQueryString);
                formattedQuery.forEach(sqlToExecute -> {
                    requestInfos.addAll(handle.createQuery(sqlToExecute).mapToMap().stream().collect(Collectors.toList()));
                });
            });

        }catch(Exception e){
            log.info(aMarker, "Failed in executed formated query {} for this sor item {}", e,sorItem);
        }
        return requestInfos;
    }

    public static String getSecurePassword(String password, byte[] salt) {

        String generatedPassword = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(salt);
            byte[] bytes = md.digest(password.getBytes());
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            generatedPassword = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return generatedPassword;
    }

    private static byte[] getSalt() throws NoSuchAlgorithmException {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        return salt;
    }
    @Builder
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class QrReader{
        private List<String> qr;
        private List<String> barcode;
    }
}
