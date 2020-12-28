package com.edowidagdo.finalproject.restapi.controller;

import com.edowidagdo.finalproject.bank.model.*;
import com.edowidagdo.finalproject.restapi.rabbitmq.ApiReceive;
import com.edowidagdo.finalproject.restapi.rabbitmq.ApiSend;
import com.google.gson.Gson;
import org.json.simple.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ApiController {
    public final ApiReceive receiver = new ApiReceive();

    // -------------------Register Nasabah-------------------------------------------
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<?> registerNasabah(@RequestBody Register register) {
        try {
            ApiSend.addNasabah(new Gson().toJson(register));
            receiver.receiveFromDatabase();
            JSONObject object = new JSONObject();
            object.put("response",200);
            object.put("status","Success");
            object.put("message","Success Add Data Nasabah");
            return new ResponseEntity<>(object, HttpStatus.OK);
        }catch (Exception e){
            System.out.println("error = " + e);
            JSONObject object = new JSONObject();
            object.put("response",400);
            object.put("status","Error");
            object.put("message","Error Add Nasabah");
            return new ResponseEntity<>(object, HttpStatus.OK);
        }
    }

    // -------------------Login User-------------------------------------------
    @RequestMapping(value = "/login/", method = RequestMethod.POST)
    public ResponseEntity<?> loginNasabah(@RequestBody Login login) {
        try {
            ApiSend.login(new Gson().toJson(login));
            return new ResponseEntity<>(receiver.loginApiRes(), HttpStatus.OK);
        }catch (Exception e){
            System.out.println("error = " + e);
            JSONObject object = new JSONObject();
            object.put("response",400);
            object.put("status","Error");
            object.put("message","Error Login, Please Check Username or Password!!!");
            return new ResponseEntity<>(object, HttpStatus.OK);
        }
    }

    // -------------------Logout User-------------------------------------------
    @RequestMapping(value = "/logout/", method = RequestMethod.PUT)
    public ResponseEntity<?> logoutNasabah(@RequestBody Login login) {
        try {
            ApiSend.logout(new Gson().toJson(login));
            return new ResponseEntity<>(receiver.logoutApiRes(), HttpStatus.OK);
        }catch (Exception e){
            System.out.println("error = " + e);
            JSONObject object = new JSONObject();
            object.put("response",400);
            object.put("status","Error");
            object.put("message","Error Logout!!!");
            return new ResponseEntity<>(object, HttpStatus.OK);
        }
    }

    // -------------------Cek Saldo-------------------------------------------
    @RequestMapping(value = "/checksaldo/{username}", method = RequestMethod.GET)
    public ResponseEntity<?> checkSaldo(@PathVariable("username") String username) {
        try {
            ApiSend.checkSaldo(username);
            return new ResponseEntity<>(receiver.checkSaldo(), HttpStatus.OK);
        } catch (Exception e) {
            System.out.println("Error = " + e);
            JSONObject object = new JSONObject();
            object.put("response", 400);
            object.put("status", "Error");
            object.put("message", "Error login, Please check Username and Password");
            return new ResponseEntity<>(object, HttpStatus.OK);
        }
    }

    // -------------------Get Total Tagihan----------------------------------------
    @RequestMapping(value = "/totaltagihan/{id_pelanggan}", method = RequestMethod.GET)
    public ResponseEntity<?> getTotalTagihan(@PathVariable("id_pelanggan") String id_pelanggan) {
        try {
            ApiSend.getTotalTagihan(id_pelanggan);
            ApiResponse apiResponse = new ApiResponse();
            apiResponse.setData(new Gson().fromJson(receiver.getTagihanPlnServer(), Tagihan.class));
            apiResponse.setMessage("Mantap");
            apiResponse.setStatus("Success");
            apiResponse.setResponse(200);
            return new ResponseEntity<>(apiResponse, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println("Error : " + e);
            JSONObject object = new JSONObject();
            object.put("response", 400);
            object.put("status", "Error");
            object.put("message", "Error Login, Please check Username and Password");
            return new ResponseEntity<>(object, HttpStatus.OK);
        }
    }

}
