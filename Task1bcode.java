import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Task1bcode {
    public static void main(String[] args) throws SQLException {
        String url = "jdbc:sqlserver://EN411405;user=t1;password=Ottm3NQGjDfg2BMucpfN;databaseName=BikeStores";
        Connection connection = DriverManager.getConnection(url);
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

        try {
            Connection connectionGet = DriverManager.getConnection(url);
            Statement sqlquery = connectionGet.createStatement();


            // Insert
            sqlquery.executeUpdate(
                    "insert into sales.stores " +
                            "(store_name, phone, email, street, city, state, zip_code) " +
                            "values " +
                            "('Japonia Bikes', 534560, 'jbikes@gmail.com', '11 Jp street', 'Tokio', 'Japonia', 2090)"
            );

            // Update
            sqlquery.executeUpdate(
                    "update sales.stores " +
                            "set phone = '534502'" +
                            "where store_name = 'Japonia Bikes' and city = 'Tokio'"
            );

            // Delete
           sqlquery.executeUpdate(
                    "delete from sales.stores " +
                            "where store_name = 'Japonia Bikes' and zip_code  = '2090'"
           );


            // Bulk insert

                sqlquery.executeUpdate(
                        "BULK INSERT sales.stores FROM 'C:\\Work\\stores.csv' with (FIRSTROW = 2, FIELDTERMINATOR = ',', ROWTERMINATOR = '\\n') ");


            System.out.print("The .csv file was read with success. In the sale.stores table were successfully added 100 stores. \n");
            System.out.print("The results of the query are the following:\n");
            String SQL = "SELECT * FROM sales.stores WHERE state like '%MD%'";
            ResultSet resultSet = sqlquery.executeQuery(SQL);
            ResultSetMetaData rsMetaData = resultSet.getMetaData();

            while (resultSet.next()) {
                int nrCol = rsMetaData.getColumnCount();
                StringBuilder row = new StringBuilder("|| ");
                for (int i = 1; i <= nrCol; i++) {
                    row.append(resultSet.getString(i)).append(" || ");

                }
                System.out.println(row);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    }