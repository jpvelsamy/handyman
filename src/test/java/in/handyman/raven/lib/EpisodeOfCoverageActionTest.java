package in.handyman.raven.lib;

import in.handyman.raven.lambda.doa.audit.ActionExecutionAudit;
import in.handyman.raven.lib.model.EpisodeOfCoverage;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
class EpisodeOfCoverageActionTest {

    @Test
    public void episodeOfCoverageTest() throws Exception {

        EpisodeOfCoverage episodeOfCoverage= EpisodeOfCoverage.builder()
                .name("Episode of coverage")
                .resourceConn("intics_agadia_db_conn")
                .originId("INT-1")
                .totalPages("5")
                .outputTable("eoc_splitting.eoc_result_138971395735140128")
                .eocIdCount("0")
                .eocGroupingItem("EOC_ID")
                .groupId("1")
               .filepath("SELECT asset.file_path,sot.origin_id,asset.file_id,sot.paper_no\n" +
                        "                             FROM info.source_of_truth sot\n" +
                        "                             JOIN info.asset asset\n" +
                        "                             ON asset.file_id=sot.preprocessed_file_id\n" +
                        "                             WHERE sot.origin_id='INT-1';")
                .value("SELECT '${gen_group_id.group_id}' AS group_id,string_agg(predicted_value,'-') AS answer,min(paper_no) AS start_no,max(paper_no) AS end_no ,'${file_count.total_page}' AS total_pages\n" +
                        "\t\t\t\t\t\t\tFROM score.aggregation_evaluator\n" +
                        "\t\t\t\t\t\t\tWHERE origin_id='INT-1' ")
                .qrInput(" SELECT '${gen_group_id.group_id}' AS group_id,extracted_value AS answer, min(paper_no) AS start_no, max(paper_no) AS end_no,'${file_count.total_page}' AS total_pages\n" +
                        "                           FROM qr_extraction.qr_extraction_result_138971395735140128\n" +
                        "                           WHERE origin_id='INT-1' AND qr_format='DATAMATRIX' group by extracted_value")
                .pndValue("     SELECT '${group_id}' AS group_id,string_agg(predicted_value,'-') AS answer,min(paper_no) AS start_no,max(paper_no) AS end_no ,'${file_count.total_page}' AS total_pages\n" +
                        "                            FROM score.aggregation_evaluator\n" +
                        "                            WHERE origin_id='${origin_id}'  and predicted_value !='' and predicted_value is not null;")
                .build();

        final ActionExecutionAudit action = ActionExecutionAudit.builder()
                .build();
        action.setRootPipelineId(11011L);
        action.getContext().put("copro.qr-attribution.url", "http://localhost:10189/copro/qr-reader");
        action.getContext().put("read.batch.size","1");
        action.getContext().put("write.batch.size","1");
        action.getContext().put("consumer.API.count","1");
        EpisodeOfCoverageAction episodeOfCoverageAction=new EpisodeOfCoverageAction(action,log,episodeOfCoverage);
        episodeOfCoverageAction.execute();
    }



}