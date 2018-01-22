package com.application.triptopia.Service;

import com.application.triptopia.Dao.MySqlAppDao;
import com.application.triptopia.Entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

@Service
public class EmployeeService {

    @Qualifier("MySQLData")
    @Autowired
    private MySqlAppDao appDao;

    public Collection<Employee> getAllEmployee(){
        return appDao.getAllEmployee();
    }

    public List<Map<String, Object>> getEmployee(int id){
        return this.appDao.getEmployee(id);
    }

    public void deleteEmployee(int id) {
        this.appDao.deleteEmployee(id);
    }

    public void updateEmployee(Employee employee){
        this.appDao.updateEmployee(employee);
    }

    public void insertEmployee(Employee employee) {
        this.appDao.insertEmployeeToDB(employee);
    }

    public Collection<Flight> getAllFlights(){
        return this.appDao.getAllFlight();
    }

    public Collection<Reservation> getReservationsByFlightNumber(String airlineId, int flightNo) {
        return this.appDao.getReservationsByFlightNumber(airlineId, flightNo);
    }

    public Collection<Reservation> getReservationsByCustomerName(String firstName, String lastName) {
        return this.appDao.getReservationsByCustomerName(firstName, lastName);
    }


    public Collection<SalesReport> getSalesReport(String date) {
        return this.appDao.getSalesReport(date);
    }

    public Collection<Revenue> getRevenueByFlightNumber(String airlineId, int flightNo) {
        return this.appDao.getRevenueByFlightNumber(airlineId, flightNo);
    }

    public Collection<Revenue> getRevenueByCity(String airlineId) {
        return this.appDao.getRevenueByCity(airlineId);
    }

    public Collection<Revenue> getRevenueByCustomer(int accountId) {
        return this.appDao.getRevenueByCustomer(accountId);
    }

    public Collection<Employee> getCustomerRepOfMaxRevenue() {

        return this.appDao.getCustomerRepOfMaxRevenue();
    }

    public List<Map<String, Object>> getMostActiveFlights() {
        return this.appDao.getMostActiveFlights();
    }

    public List<Map<String, Object>> getCustomersOnFlight(String airlineId, int flightNo, int legNo) {
        return this.appDao.getCustomersOnFlight(airlineId, flightNo, legNo);
    }

    public void recordReservation(Reservation reservation){
        this.appDao.recordReservation(reservation);
    }

    public void addCustomer(Customer customer) {
        this.appDao.addCustomer(customer);
    }

    public Collection<Customer> getAllCustomers() {
        return appDao.getAllCustomers();
    }

    public void makeOneWayReservation(Ticket ticket) {
        appDao.makeOneWayReservation(ticket);
    }

    public void makeRoundTripReservation(Ticket[] ticket) {
        appDao.makeRoundTripReservaion(ticket);
    }

    public void updateCustomer(Customer customer, int i) {
        appDao.updateCustomer(customer, i);
    }

    public void deleteCustomer(int i) {
        appDao.deleteCustomer(i);
    }

    public void recommendedFlightsForCustomer(int i) {
        appDao.recommendedFlightsForCustomer(i);
    }


    public List<Map<String, Object>> searchRoute(String depAirportId, String arrAirportId, String depTime, String arrTime) {
        return appDao.searchRoute(depAirportId, arrAirportId, depTime, arrTime);
    }

    public List<Map<String, Object>> getAllReservations(Integer accountNo) {
        return appDao.getAllReservations(accountNo);
    }

    public List<Map<String,Object>> getCurrentlReservations(Integer accountNo) {
        return appDao.getCurrentReservations(accountNo);
    }
}
