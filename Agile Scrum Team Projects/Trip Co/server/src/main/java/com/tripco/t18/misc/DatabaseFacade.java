package com.tripco.t18.misc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;

/*
Reference: 
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DatabaseFacade {
    // db configuration information
    private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private static final String MY_URL;
    private static final String USER;
    private static final String PASS;

    // ENVIRONMENT INITIALIZATION BLOCK, SEE:
    static {
        String isDev = System.getenv("CS314_ENV");
        String isTravis = System.getenv("TRAVIS");

        if (isTravis != null && isTravis.equals("true")) {
            MY_URL = "";
            USER = "";
            PASS = ;
        } else if (isDev != null && isDev.equals("development")) {
            MY_URL = "";
            USER = "";
            PASS = "";
        } else {
            MY_URL = "";
            USER = "";
            PASS = "";
        }

    }

    // Selection Query
    private static final String SELECT_PREFIX = "SELECT ";
    // Counting Query
    private static final String COUNT_PREFIX = "SELECT COUNT(*) AS count ";
    // World search query
    private static final String WORLD_FROM_CLAUSE = " FROM world w INNER JOIN country c INNER JOIN region r INNER JOIN continent ct ON w.iso_country=c.id AND w.iso_region=r.id AND w.continent=ct.id ";
    private static final String WORLD_LIKE_SUFFIX = WORLD_FROM_CLAUSE + " WHERE (w.name like ? OR w.municipality like ? OR w.keywords like ? OR w.iso_region like ?) ";
    // Limit number of search results
    private static final String LIMITER = " LIMIT ?";


    private static final Logger logger = LoggerFactory.getLogger("DatabaseFacade");


    // Searches through the colorado DB table for locations that might match the search string.
    public static List<Map> getLocationsBySearchString(String search, int limit, List<String> attributes) {
        return getLocationsBySearchString(search, limit, attributes, null);
    }

    public static List<Map> getLocationsBySearchString(String search, int limit, List<String> attributes, List<Map<String, Object>> filters) {
        List<Map> results = new ArrayList<>();
        String searchQuery = SELECT_PREFIX + String.join(",", attributes) + WORLD_LIKE_SUFFIX  + buildFilters(filters) + LIMITER;
        try {
            Class.forName(JDBC_DRIVER);
            try (Connection conn = DriverManager.getConnection(MY_URL, USER, PASS)) {
                PreparedStatement searchStatement = conn.prepareStatement(searchQuery);
                setStringsFor(searchStatement, 1, 4, "%" + cleanString(search) + "%");

                searchStatement.setInt(5, limit);

                ResultSet records = searchStatement.executeQuery();

                ResultSetMetaData md = records.getMetaData();

                while (records.next()) {
                    results.add(parseLocation(records, md));
                }
            }
        } catch (SQLException e) {
            logger.error("SQLException caught in Search: " + e.getMessage());
        } catch (Exception e){
            logger.error(e.getMessage());
        }
        return results;
    }

    public static int countLocationsBySearchString(String search, List<Map<String, Object>> filters) {
        int count = -1;
        String query = COUNT_PREFIX + WORLD_LIKE_SUFFIX + buildFilters(filters);
        try {
            Class.forName(JDBC_DRIVER);
            try (Connection conn = DriverManager.getConnection(MY_URL, USER, PASS)) {
                PreparedStatement countStatement = conn.prepareStatement(query);
                setStringsFor(countStatement, 1, 4, "%" + cleanString(search) + "%");

                ResultSet rs = countStatement.executeQuery();
                rs.next();
                count = rs.getInt(1);
            }
        } catch (SQLException e) {
            logger.error("SQLException caught in Count: " + e.getMessage());
        } catch (Exception e){
            logger.error(e.getMessage());
        }
        return count;
    }

    public static List<String> getCountryNames(){
        List<String> countryNames = new ArrayList<>(250); // We recognize 243 countries with room for more.
        try {
            Class.forName(JDBC_DRIVER);
            try (Connection conn = DriverManager.getConnection(MY_URL, USER, PASS)) {
                Statement query = conn.createStatement();
                ResultSet countryData = query.executeQuery("SELECT name FROM country ORDER BY name");

                while(countryData.next()){
                    countryNames.add(countryData.getString("name"));
                }
            }
        } catch (SQLException e) {
            logger.error("SQLException caught in Count: " + e.getMessage());
        } catch (Exception e){
            logger.error(e.getMessage());
        }
        return countryNames;
    }

    // Counts locations in colorado that match a given string.
    public static int countLocationsBySearchString(String search) {
        return countLocationsBySearchString(search, null);
    }

    private static HashMap<String, Object> parseLocation(ResultSet rs, ResultSetMetaData md) throws SQLException{
        int count = md.getColumnCount();
        HashMap<String, Object> location = new HashMap<>(count);

        for (int i = 1; i <= count; i++) {
            location.put(md.getColumnLabel(i), rs.getObject(i));
        }
        return location;
    }

    /*
    Replaces all non alphanumeric characters with an underscore. This allows the server to receive special characters
    and not die!
     */
    private static String cleanString(String s){
        return s.replaceAll("[^A-Za-z0-9]", "_");
    }

    private static String buildFilters(List<Map<String, Object>> filters){
        if(filters == null){
            return "";
        }

        StringBuilder result = new StringBuilder();


        for(int j = 0; j < filters.size(); j++){
            result.append(serializeFilter(filters.get(j)));

            // If we have additional filter types to add
            if(j != filters.size() - 1 && filters.size() > 2) {
                result.append(" AND ");
            }
        }

        // If we actually built a filter string, make sure we AND it.
        if(!result.toString().equals("")){
            result.insert(0, " AND ");
        }
        return result.toString();
    }

    private static String serializeFilter(Map<String, Object> filter){
        StringBuilder result = new StringBuilder();

        String name = (String)filter.get("name");
        ArrayList<String> values = (ArrayList<String>)filter.get("values");
        if(name.equals("country")){
            result.append(serializeFilterFields("c.name", values));
        } else {
            result.append(serializeFilterFields(name, values));
        }

        return result.toString();
    }

    private static String serializeFilterFields(String name, List<String> values){
        StringBuilder result = new StringBuilder();

        if(values.size() >= 1) {
            result.append(" (");
            for (int i = 0; i < values.size(); i++) {
                // (name='value' OR ... )
                result.append(name);
                result.append(" like ");
                result.append("\'%");
                result.append(values.get(i));
                result.append("%\'");
                if (i != values.size() - 1) {
                    result.append(" OR ");
                }
            }
            result.append(") ");
        }

        return result.toString();
    }



    private static void setStringsFor(PreparedStatement ps, int beginIndex, int endIndex, String value) throws SQLException {
        for (int i = beginIndex; i <= endIndex; i++) {
            ps.setString(i, value);
        }
    }
}
