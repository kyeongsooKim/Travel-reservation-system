package com.application.triptopia.Dao;

import com.application.triptopia.Entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.*;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;

@Repository("MySQLData")
public class MySqlAppDao{

    @Autowired
    private JdbcTemplate jdbcTemplate;




    private static class EmployeeRowMapper implements RowMapper<Employee>{
        @Override
        public Employee mapRow(ResultSet resultSet, int i) throws SQLException {
            Employee employee = new Employee();
            employee.setFirstName(resultSet.getString("FirstName"));
            employee.setLastName(resultSet.getString("LastName"));
            employee.setAddress(resultSet.getString("Address"));
            employee.setCity(resultSet.getString("City"));
            employee.setState(resultSet.getString("State"));
            employee.setZipCode(resultSet.getInt("ZipCode"));
            employee.setPersonId(resultSet.getInt("Id"));
            employee.setSsn(resultSet.getInt("SSN"));
            employee.setManager(resultSet.getBoolean("IsManager"));
            employee.setStartDate(resultSet.getString("StartDate"));
            employee.setHourlyRate(resultSet.getDouble("HourlyRate"));
            return employee;
        }
    }

    private static class CustomerRowMapper implements RowMapper<Customer>{
        @Override
        public Customer mapRow(ResultSet resultSet, int i) throws SQLException {
            Customer customer = new Customer();
            customer.setFirstName(resultSet.getString("FirstName"));
            customer.setLastName(resultSet.getString("LastName"));
            customer.setAddress(resultSet.getString("Address"));
            customer.setCity(resultSet.getString("City"));
            customer.setState(resultSet.getString("State"));
            customer.setZipCode(resultSet.getInt("ZipCode"));
            customer.setPersonId(resultSet.getInt("Id"));
            customer.setCreditCardNo(resultSet.getString("creditCardNo"));
            customer.setEmail(resultSet.getString("email"));
            customer.setCreationDate(resultSet.getString("creationDate"));
            customer.setRating(resultSet.getInt("rating"));
            return customer;
        }
    }
    private static class FlightRowMapper implements RowMapper<Flight>{
        @Override
        public Flight mapRow(ResultSet resultSet, int i) throws SQLException {
            Flight flight = new Flight();
            flight.setAirlineId(resultSet.getString("airlineId"));
            flight.setFlightNo(resultSet.getInt("flightNo"));
            flight.setNoOfSeats(resultSet.getInt("noOfSeats"));
            flight.setDaysOperating(resultSet.getString("daysOperating"));
            flight.setMaxLengthOfStay(resultSet.getInt("maxLengthOfStay"));
            flight.setMinLengthOfStay(resultSet.getInt("minLengthOfStay"));
            return flight;
        }
    }

    private static class ReservationRowMapper implements RowMapper<Reservation> {
        @Override
        public Reservation mapRow(ResultSet resultSet, int i) throws SQLException {
            Reservation reservation = new Reservation();
            reservation.setResrNo(resultSet.getInt("resrNo"));
            reservation.setResrDate(resultSet.getTimestamp("resrDate").toString());
            reservation.setBookingFee(resultSet.getDouble("bookingFee"));
            reservation.setTotalFare(resultSet.getDouble("totalFare"));
            reservation.setRepSSN(resultSet.getInt("repSSN"));
            reservation.setAccountNo(resultSet.getInt("accountNo"));
            return reservation;
        }
    }

    private static class SalesReportRowMapper implements RowMapper<SalesReport>{
        @Override
        public SalesReport mapRow(ResultSet resultSet, int i) throws SQLException {
            SalesReport sr = new SalesReport(resultSet.getTimestamp("resrDate").toString(),
                    resultSet.getDouble("totalFare"), resultSet.getDouble("bookingFee"));

            return sr;
        }
    }

    private static class RevenueRowMapper implements RowMapper<Revenue>{
        @Override
        public Revenue mapRow(ResultSet resultSet, int i) throws SQLException {
            return new Revenue(resultSet.getInt("revenue"));
        }
    }

    public Collection<Employee> getAllEmployee() {

        final String sql = "SELECT * FROM reservation_schema.employee e, reservation_schema.person p WHERE p.Id = e.id";
        List<Employee> queryViewEmployees = jdbcTemplate.query(sql, new EmployeeRowMapper());
        return queryViewEmployees;
    }

    public List<Map<String, Object>> getEmployee(int id) {
        final String sql = "SELECT * FROM reservation_schema.employee em, reservation_schema.person p WHERE p.Id = em.id AND em.SSN = ?";
        return jdbcTemplate.queryForList(sql, id);
    }

    public void deleteEmployee(int id) {
        final String sql = "DELETE FROM Employee WHERE Employee.SSN = ?";
        jdbcTemplate.update(sql, id);
    }

    public void updateEmployee(Employee employee) {
        final String sql = "UPDATE Employee\n" +
                "    SET Employee.IsManager=?, Employee.StartDate=?, Employee.HourlyRate=?\n" +
                "    WHERE Employee.SSN=?";
        Object[] param = new Object[]{employee.isManager(), employee.getStartDate(), employee.getHourlyRate(), employee.getSsn()};
        jdbcTemplate.update(sql, param);
    }

    public void insertEmployeeToDB(Employee employee) {
        Person p = new Person(employee.getFirstName(), employee.getLastName(), employee.getAddress(), employee.getCity(), employee.getState(), employee.getZipCode());
        Number key = addPerson(p);
        /*String sqlAddPerson = "INSERT INTO Person(FirstName,LastName,Address,City,State,ZipCode)\n" +
                "values(?,?,?,?,?,?);";

        KeyHolder key = new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(sqlAddPerson, new String[]{"firstName", "lastName", "address", "city", "state", "zipCode" });
                ps.setString(1, employee.getFirstName());
                ps.setString(2, employee.getLastName());
                ps.setString(3, employee.getAddress());
                ps.setString(4, employee.getCity());
                ps.setString(5, employee.getState());
                ps.setInt(6, employee.getZipCode());
                return ps;
            }
        }, key);*/

        String sql = "INSERT INTO reservation_schema.Employee(Id,SSN,IsManager,StartDate,HourlyRate) VALUES (?, ?, ?, ?, ?)";
        DateFormat formatter = new SimpleDateFormat("yyyy-mm-dd");
        try {
            Date date = formatter.parse(employee.getStartDate());
            jdbcTemplate.update(sql, key, employee.getSsn(), employee.isManager(), date, employee.getHourlyRate());
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

    public Collection<Flight> getAllFlight() {
        String sql = "SELECT * FROM flight";
        List<Flight> query = jdbcTemplate.query(sql, new FlightRowMapper());
        return query;
    }

    public Collection<Reservation> getReservationsByFlightNumber(String airlineId, int flightNo) {
        String sql = "SELECT DISTINCT reservation.* \n" +
                "    FROM reservation, includes\n" +
                "WHERE reservation.ResrNo = includes.ResrNo AND includes.AirlineId=? AND includes.FlightNo = ?";
        List<Reservation> query = jdbcTemplate.query(sql, new ReservationRowMapper(), airlineId, flightNo);
        return query;
    }

    public Collection<Reservation> getReservationsByCustomerName(String firstName, String lastName) {
        String sql = "SELECT DISTINCT reservation.*\n" +
                "\tFROM reservation, reservationPassenger, person\n" +
                "\tWHERE reservation.ResrNo = reservationPassenger.ResrNo\n" +
                "\t\tAND reservationPassenger.Id = person.Id \n" +
                "        AND person.FirstName = ? AND person.LastName = ?";

        List<Reservation> query = jdbcTemplate.query(sql, new ReservationRowMapper(), firstName, lastName);
        return query;
    }

    public Collection<SalesReport> getSalesReport(String date){
        String sql = "SELECT Reservation.ResrDate, Reservation.TotalFare, Reservation.BookingFee\n" +
                "    FROM Reservation\n" +
                "    WHERE MONTH(Reservation.ResrDate) = MONTH(?) AND  YEAR(Reservation.ResrDate) = YEAR(?)";
        return jdbcTemplate.query(sql, new SalesReportRowMapper(), date, date);
    }

    public Collection<Revenue> getRevenueByFlightNumber(String airlineId, int flightNo) {
        String sql = "SELECT SUM(Reservation.BookingFee)+SUM(Reservation.TotalFare) as revenue\n" +
                "\tFROM Reservation, Includes\n" +
                "\tWHERE Reservation.ResrNo = Includes.ResrNo AND\n" +
                "   \t Includes.FlightNo = ? AND\n" +
                "   \t Includes.AirlineId = ?\n";
        //Integer result = jdbcTemplate.queryForObject(sql, new Object[]{flightNo, airlineId}, Integer.class);
        return jdbcTemplate.query(sql, new RevenueRowMapper(), flightNo, airlineId);
        //return new ArrayList<Integer>(Arrays.asList(result));
    }

    public Collection<Revenue> getRevenueByCity(String city) {
        String sql = "SELECT SUM(Reservation.BookingFee) +SUM(Reservation.TotalFare) as revenue\n" +
                "\tFROM Reservation, Includes, Leg, Airport\n" +
                "\tWHERE Reservation.ResrNo = Includes.ResrNo AND\n" +
                "   \t Includes.AirlineId = Leg.AirlineId AND\n" +
                "    \tIncludes.FlightNo = Leg.FlightNo AND\n" +
                "    \tIncludes.LegNo = Leg.LegNo AND\n" +
                "    \tLeg.DepAirportID = Airport.Id AND\n" +
                "    \tAirport.City = ?\n";
        return jdbcTemplate.query(sql, new RevenueRowMapper(), city);
    }

    public Collection<Revenue> getRevenueByCustomer(int accountId) {
        String sql = "SELECT SUM(Reservation.BookingFee) +SUM(Reservation.TotalFare) as revenue\n" +
                "\tFROM Reservation\n" +
                "\tWHERE Reservation.AccountNo = ?\n";
        return jdbcTemplate.query(sql, new RevenueRowMapper(), accountId);
    }

    public Collection<Employee> getCustomerRepOfMaxRevenue() {
        String s2 = "(SELECT RepSSN FROM (SELECT MAX(sumOfFees) as maxSumOfFees FROM ( SELECT RepSSN, SUM(reservation.BookingFee) as sumOfFees FROM reservation GROUP BY RepSSN) as aa ) as ab, ( SELECT RepSSN, SUM(reservation.BookingFee) as sumOfFees FROM reservation GROUP BY RepSSN ) as aa WHERE aa.sumOfFees = ab.maxSumOfFees)";
        int repSSN = jdbcTemplate.queryForObject(s2, Integer.class);
        String s3 = "SELECT * FROM employee e, person p WHERE e.SSN = ? AND e.Id = p.Id";

        return jdbcTemplate.query(s3, new EmployeeRowMapper(),repSSN);
    }

    public List<Map<String, Object>> getMostActiveFlights(){
        String sql = "SELECT FlightNo, AirlineID FROM ( SELECT MAX(numFlights) as maxNumFlights FROM ( SELECT FlightNo, AirlineID, COUNT(*) AS numFlights FROM Leg GROUP BY Leg.FlightNo,Leg.AirlineID) as tmp34) as table1, ( SELECT FlightNo, AirlineID, COUNT(*) AS numFlights FROM Leg GROUP BY Leg.FlightNo,Leg.AirlineID)  as sre  WHERE sre.numFlights = table1.maxNumFlights";
        return jdbcTemplate.queryForList(sql);
    }

    public List<Map<String, Object>> getCustomersOnFlight(String airlineId, int flightNo, int legNo) {
        String sql = "SELECT Customer.*\n" +
                "\tFROM Customer, Includes, Reservation, ReservationPassenger\n" +
                "\tWHERE Includes.ResrNo = Reservation.ResrNo AND\n" +
                "   \t Reservation.ResrNo = ReservationPassenger.ResrNo AND\n" +
                "   \t ReservationPassenger.AccountNo = Customer.AccountNo AND\n" +
                "   \t Includes.FlightNo = ? AND\n" +
                "    \tIncludes.AirlineId = ? AND\n" +
                "    \tIncludes.LegNo = ?";
        return jdbcTemplate.queryForList(sql, flightNo, airlineId, legNo);
    }

    public void recordReservation(Reservation reservation){
        String sql = "INSERT IGNORE INTO Reservation(ResrDate, BookingFee, TotalFare, RepSSN, AccountNo)\n" +
                "    VALUES(?,?,?,?,?)";
        jdbcTemplate.update(sql, reservation.getReservationAsArray());
    }

    public void addCustomer(Customer customer){

        Person p = new Person(customer.getFirstName(), customer.getLastName(), customer.getAddress(), customer.getCity(), customer.getState(), customer.getZipCode());
        Number key = addPerson(p);
        String sqlAddCustomer = "INSERT INTO Customer(Id,CreditCardNo, phoneNo, Email,CreationDate,Rating)\n" +
                "\tvalues(?,?, ?, ?,?, ?)";
        DateFormat format = new SimpleDateFormat("yyyy-mm-dd");
        try {
            Date date = format.parse(customer.getcreationDate());
            jdbcTemplate.update(sqlAddCustomer, key, customer.getCreditCardNo(), customer.getPhoneNo(), customer.getEmail(), date, customer.getRating());
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public Number addPerson(Person p){
        String sqlAddPerson = "INSERT INTO Person(FirstName,LastName,Address,City,State,ZipCode)\n" +
                "values(?,?,?,?,?,?);";

        KeyHolder key = new GeneratedKeyHolder();
        //noinspection Duplicates
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(sqlAddPerson, new String[]{"firstName", "lastName", "address", "city", "state", "zipCode" });
                ps.setString(1, p.getFirstName());
                ps.setString(2, p.getLastName());
                ps.setString(3, p.getAddress());
                ps.setString(4, p.getCity());
                ps.setString(5, p.getState());
                ps.setInt(6, p.getZipCode());
                return ps;
            }
        }, key);

        return key.getKey();
    }

    public Collection<Customer> getAllCustomers() {
        final String sql = "SELECT * FROM reservation_schema.customer c, reservation_schema.person p WHERE p.Id = c.id";
        List<Customer> queryViewCustomers = jdbcTemplate.query(sql, new CustomerRowMapper());
        return queryViewCustomers;
    }

    public void makeOneWayReservation(Ticket ticket) {

        jdbcTemplate.update("CALL `reservation_schema`.`makeOneWayReservation`(?, ?, ?, ?, ?, ?, ?)", ticket.getAccountNo(), ticket.getAirlineId(), ticket.getFlightNo(), ticket.getSeatClass(), ticket.getSeatNo(), ticket.getMeal(), ticket.getLegNo());
    }

    public void makeRoundTripReservaion(Ticket[] ticket) {
        jdbcTemplate.update("CALL `reservation_schema`.`makeRoundTripReservation`(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)", ticket[0].getAccountNo(), ticket[0].getAirlineId(), ticket[0].getFlightNo(), ticket[0].getLegNo(), ticket[1].getAirlineId(), ticket[1].getFlightNo(), ticket[1].getLegNo(), ticket[0].getSeatClass(), ticket[0].getSeatNo(), ticket[0].getMeal());

    }

    public void updateCustomer(Customer customer, int i) {
        if(customer.getCreditCardNo()!=null){
            jdbcTemplate.update("CALL `reservation_schema`.`editCustomerCreditCard`(?, ?)", customer.getCreditCardNo(), i);
        }
        if(customer.getPhoneNo()!=null){
            jdbcTemplate.update("CALL `reservation_schema`.`editCustomerPhoneNo`(?, ?)", customer.getPhoneNo() , i);
        }
        if(customer.getEmail()!=null){
            jdbcTemplate.update("CALL `reservation_schema`.`editCustomerEmail`(?, ?)", customer.getEmail() , i);
        }
        if(customer.getRating()!=0){
            jdbcTemplate.update("CALL `reservation_schema`.`editCustomerRating`(?, ?)", customer.getRating() , i);
        }
    }

    public void deleteCustomer(int i) {
        jdbcTemplate.update("CALL `reservation_schema`.`removeCustomer`(?)", i);
    }


    public void recommendedFlightsForCustomer(int i) {
        jdbcTemplate.update("CALL `reservation_schema`.`getSuggestedLegs`(?)", i);
    }

    public List<Map<String,Object>> searchRoute(String depAirportId, String arrAirportId, String depTime, String arrTime) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        List<Map<String, Object>> maps = null;
        try {
            Date depDate = sdf.parse(depTime);
            Date arrDate = sdf.parse(arrTime);
            maps = jdbcTemplate.queryForList("CALL `reservation_schema`.`findRoutes`(?, ?, ?, ?)", depAirportId, arrAirportId, new Timestamp(depDate.getTime()), new Timestamp(arrDate.getTime()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return maps;
    }

    public List<Map<String, Object>> getAllReservations(Integer accountNo) {
        String sql = "SELECT * from reservation WHERE reservation.accountNo = ?";
        return jdbcTemplate.queryForList(sql, accountNo);
    }

    public List<Map<String,Object>> getCurrentReservations(Integer accountNo) {
        String sql = "SELECT * " +
                "FROM reservation, reservation_schema.includes, reservation_schema.leg " +
                "WHERE reservation.AccountNo = ? AND reservation.ResrNo = includes.ResrNo " +
                "AND includes.LegNo = leg.legNo AND  DATE(leg.DepTime)  between CURRENT_DATE() AND '9999-12-31' ";
        return jdbcTemplate.queryForList(sql, accountNo);
    }
}
