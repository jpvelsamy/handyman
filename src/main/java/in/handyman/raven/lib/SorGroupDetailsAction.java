package in.handyman.raven.lib;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zaxxer.hikari.HikariDataSource;
import in.handyman.raven.exception.HandymanException;
import in.handyman.raven.lambda.access.ResourceAccess;
import in.handyman.raven.lambda.action.ActionExecution;
import in.handyman.raven.lambda.action.IActionExecution;
import in.handyman.raven.lambda.doa.audit.ActionExecutionAudit;
import in.handyman.raven.lib.model.SorGroupDetails;
import in.handyman.raven.util.CommonQueryUtil;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

import java.sql.Connection;
import java.sql.Statement;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Auto Generated By Raven
 */
@ActionExecution(
        actionName = "SorGroupDetails"
)
public class SorGroupDetailsAction implements IActionExecution {
    private final ActionExecutionAudit action;

    private final Logger log;

    private final SorGroupDetails sorGroupDetails;

    private final Set<String> writeBuffer = new HashSet<>();

    private final Marker aMarker;

    public SorGroupDetailsAction(final ActionExecutionAudit action, final Logger log,
                                 final Object sorGroupDetails) {
        this.sorGroupDetails = (SorGroupDetails) sorGroupDetails;
        this.action = action;
        this.log = log;
        this.aMarker = MarkerFactory.getMarker(" SorGroupDetails:" + this.sorGroupDetails.getName());
    }

    public static JSONObject mergeJSONObjects(JSONObject... jsonObjects) {
        JSONObject mergedJSON = new JSONObject();
        try {
            for (JSONObject temp : jsonObjects) {
                Iterator<String> keys = temp.keys();
                while (keys.hasNext()) {
                    String key = keys.next();
                    mergedJSON.put(key, temp.get(key));
                }

            }
        } catch (JSONException e) {
            throw new RuntimeException("JSON Exception" + e);
        }
        return mergedJSON;
    }

    @Override
    public void execute() throws Exception {
        var dbSrc = sorGroupDetails.getOn();
        var sql = sorGroupDetails.getValue();
        var keyfields = sorGroupDetails.getKeyfields();
        var searchfields = sorGroupDetails.getSearchfields();
        var groupbyfields = sorGroupDetails.getGroupbyfields();

        log.info(aMarker, "id#{}, name#{}, from#{}, keyfileds#{} searchfileds-{}", action.getActionId(),
                sorGroupDetails.getName(), sorGroupDetails.getOn(), sorGroupDetails.getKeyfields(),
                sorGroupDetails.getSearchfields());

        if (sorGroupDetails.getValue() != null) {
            final HikariDataSource source = ResourceAccess.rdbmsConn(dbSrc);
            try (var conn = source.getConnection()) {
                try (var stmt = conn.createStatement()) {
                    final Map<String, String> context = action.getContext();
                    try (var rs = stmt.executeQuery(sql)) {
                        var columnCount = rs.getMetaData().getColumnCount();
                        String prevkey = "";
                        String key = "";
                        Map<String, String> preValue = new HashMap<>();
                        JSONArray groupElmentArray = null;
                        JSONObject groupElmentObject = new JSONObject();
                        while (rs.next()) {
                            ObjectMapper objectMapper = new ObjectMapper();
                            CommonQueryUtil.addKeyConfig(context, log, rs, columnCount, "");
                            String[] keysplitarr = keyfields.split(",");
                            String[] searchsplitarr = searchfields.split(",");
                            final Map<String, String> stringStringMap = objectMapper.readValue(rs.getString(1),
                                    new TypeReference<>() {
                                    });
                            final Map<String, String> stringMap = stringStringMap.entrySet().stream()
                                    .filter(field -> (Arrays.asList(searchsplitarr).contains(field.getKey())))
                                    .collect(Collectors
                                            .toMap(Map.Entry::getKey,
                                                    Map.Entry::getValue, (p, q) -> p));
                            // TODO add logic to use any number of keys
                            key = rs.getString(keysplitarr[0]) + "#" + rs.getString(keysplitarr[1]);
                            boolean isfound = false;
                            for (Map.Entry<String, String> pentry : stringMap.entrySet()) {
                                if (preValue.containsValue(pentry.getValue()) && !isfound) {
                                    groupElmentArray.put(rs.getInt(groupbyfields));
                                    groupElmentObject = mergeJSONObjects(groupElmentObject, new JSONObject(rs.getString(1)));
                                    isfound = true;
                                }
                            }
                            if (!isfound) {
                                if (groupElmentArray != null) {
                                    generateDataFrame(prevkey, groupElmentArray, groupElmentObject);
                                }
                                groupElmentArray = new JSONArray();
                                groupElmentArray.put(rs.getInt(groupbyfields));
                                groupElmentObject = new JSONObject(rs.getString(1));
                                prevkey = key;
                            }

                            preValue.clear();
                            preValue.putAll(stringMap);
                            stringMap.clear();
                        }
                        if (groupElmentArray != null)
                            generateDataFrame(key, groupElmentArray, groupElmentObject);
                    }
                }
            }
        }
        action.getContext().put(sorGroupDetails.getName(), writeBuffer.toString());
        writeToDb(writeBuffer);
    }

    private void generateDataFrame(String key, JSONArray jarray, JSONObject groupElmentObject) {
        String targetTable = sorGroupDetails.getTargettable();
        var keyfields = sorGroupDetails.getKeyfields();
        var groupbyfields = sorGroupDetails.getGroupbyfields();

        final String qryFrameBuilder = "INSERT INTO " + targetTable + " (" +
                keyfields + "," +
                groupbyfields + "," +
                "combinedvalue" +
                ") VALUES" + Constants.INSERT_STMT_VALUE_START;

        var dataFrameBuilder = new StringBuilder();
        dataFrameBuilder.append(qryFrameBuilder).append("'")
                .append(key.split("#")[0]).append("','")
                .append(key.split("#")[1]).append("','")
                .append(jarray.toString().replace("[", "{").replace("]", "}")).append("','")
                .append(groupElmentObject).append("'")
                .append(Constants.INSERT_STMT_VALUE_END).append(";");
        writeBuffer.add(dataFrameBuilder.toString());

        dataFrameBuilder.setLength(0);

    }


    private void writeToDb(Set<String> writeBuffer) {
        var dbSrc = sorGroupDetails.getOn();
        String targebtable = sorGroupDetails.getTargettable();
        var hikariDataSource = ResourceAccess.rdbmsConn(dbSrc);
        try (final Connection sourceConnection = hikariDataSource.getConnection()) {
            sourceConnection.setAutoCommit(false);
            log.info("Writing to database using conn: {} table- {}", dbSrc, targebtable);
            //TODO audit
            try (final Statement stmt = sourceConnection.createStatement()) {
                for (var s : writeBuffer) {
                    stmt.addBatch(s);
                }
                stmt.executeBatch();
                sourceConnection.commit();
                writeBuffer.clear();
            }
        } catch (Exception ex) {
            log.error("SorGroupDetailsAction: {} error closing source connection for database: {} ",
                    action.getActionId(), targebtable, ex);
            throw new HandymanException("writeToDb failed", ex, action);
        }
    }

    @Override
    public boolean executeIf() throws Exception {
        return sorGroupDetails.getCondition();
    }
}
