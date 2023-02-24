package in.handyman.raven.lib.agadia.eocsplitting;

import in.handyman.raven.lambda.doa.audit.ActionExecutionAudit;
import in.handyman.raven.lib.model.EpisodeOfCoverage;
import in.handyman.raven.util.CommonQueryUtil;
import org.jdbi.v3.core.Jdbi;
import org.slf4j.Logger;
import org.slf4j.Marker;

import java.util.*;
import java.util.stream.Collectors;

public class SorItemCoverage {

    private final Logger log;

    private final EpisodeOfCoverage episodeOfCoverage;
    private final Marker aMarker;

    private final ActionExecutionAudit action;


    public SorItemCoverage(Logger log, EpisodeOfCoverage episodeOfCoverage, Marker aMarker, ActionExecutionAudit action) {
        this.log = log;
        this.episodeOfCoverage = episodeOfCoverage;
        this.aMarker = aMarker;
        this.action = action;
    }

    public Map<String, List<Integer>> splitBySorItem(Jdbi jdbi, String sorItem){
        Map<String, List<Integer>> stringObjectMap = new HashMap<>();

        try {
            String UnformattedQueryString="SELECT predicted_value as answer,min(paper_no) as start_no,max(paper_no) as end_no \n" +
                    "          FROM score.aggregation_evaluator\n" +
                    "          WHERE origin_id= '" + episodeOfCoverage.getOriginId() +
                    "' AND sor_item_name='" +sorItem+
                    "' AND  sor_item_name='patient_dob'    group by predicted_value;" ;
            List<Map<String, Object>> eocGroupingMemberItemRequestInfos = queryExecutor(jdbi,sorItem,UnformattedQueryString);

            List<Integer> breakPointsList = new ArrayList<>();
            eocGroupingMemberItemRequestInfos.forEach(stringObjectMapInfo -> {
                final Integer startNoString = (Integer) Optional.ofNullable(stringObjectMapInfo.get("start_no")).orElse(0);
                breakPointsList.add(startNoString);
            });
            Collections.sort(breakPointsList);

            //this logic needs start_page_no and total page numbers list
            for (var eocGroupingMemberItemRequestInfo : eocGroupingMemberItemRequestInfos) {
                List<Integer> paperList = new ArrayList<>();
                Integer startNoInt = (Integer) Optional.ofNullable(eocGroupingMemberItemRequestInfo.get("start_no")).orElse(0);
                final String answerString = Optional.ofNullable(eocGroupingMemberItemRequestInfo.get("answer")).map(String::valueOf).orElse("");
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
                stringObjectMap.put(answerString, paperList);
            }

        } catch (Exception e) {
            log.info(aMarker, "<-------Episode of coverage Action for member id filter {} has failed------->" + episodeOfCoverage.getName());

        }
        return stringObjectMap;
    }

    public List<Map<String, Object>> queryExecutor(Jdbi jdbi,String sorItem,String unFormattedQueryString){
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
}
