package in.handyman.raven.lib.agadia.eocsplitting;

import in.handyman.raven.lambda.doa.audit.ActionExecutionAudit;
import in.handyman.raven.lib.model.EpisodeOfCoverage;
import in.handyman.raven.util.CommonQueryUtil;
import org.jdbi.v3.core.Jdbi;
import org.slf4j.Logger;
import org.slf4j.Marker;

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

    public Map<String, List<Integer>> splitByQrcode(Jdbi jdbi,String sorItem) throws InterruptedException {

        Map<String,List<Integer>> eocObjectMap=new HashMap<>();

        if(Objects.equals(sorItem,"qr_code")){

            List<Map<String, Object>> eocIdRequestInfo=queryExecutor(jdbi,sorItem,episodeOfCoverage.getQrInput());
            if (!eocIdRequestInfo.isEmpty()) {

                List<Integer> breakPointsList = new ArrayList<>();
                eocIdRequestInfo.forEach(eocGroupingEocIdRequestInfo -> {
                    final Integer startNoString = (Integer) Optional.ofNullable(eocGroupingEocIdRequestInfo.get("start_no")).orElse(0);
                    breakPointsList.add(startNoString);
                });
                Collections.sort(breakPointsList);


                for (var eocGroupingEocIdRequestInfo : eocIdRequestInfo) {
                    List<Integer> paperList = new ArrayList<>();
                    Integer startNoInt = (Integer) Optional.ofNullable(eocGroupingEocIdRequestInfo.get("start_no")).orElse(0);
                    String answerString = Optional.ofNullable(eocGroupingEocIdRequestInfo.get("answer")).map(String::valueOf).orElse("");
                    int totalPageInt = Integer.parseInt(episodeOfCoverage.getTotalPages());
                    int endPoint = 0;

                    try {
                        endPoint = breakPointsList.get(breakPointsList.indexOf(startNoInt) + 1);
                    } catch (Exception e) {
                        endPoint = totalPageInt + 1;
                    }
                    if (breakPointsList.indexOf(startNoInt) == 0 ) {
                        startNoInt = 1;
                    }

                    for (int i = startNoInt; i < endPoint; i++) {
                        paperList.add(i);
                    }
                    //thic code will save the result as a map with string as key and list as value
                    answerString =answerString.replaceAll("[-/#%;?\\\\]","_");
                    eocObjectMap.put(answerString, paperList);
                }
            }
        }
        return eocObjectMap;
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
            log.info(aMarker, "complete executed formated query {} for this sor item {}",unFormattedQueryString, sorItem);

        }catch(Exception e){
            log.info(aMarker, "Failed in executed formated query {} for this sor item {}", e,sorItem);
        }
        return requestInfos;
    }


}
