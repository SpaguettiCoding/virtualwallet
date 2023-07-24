package com.alkemy.cysjava.virtualwallet;



import com.alkemy.cysjava.virtualwallet.models.Account;

import com.alkemy.cysjava.virtualwallet.models.Role;

import com.alkemy.cysjava.virtualwallet.models.User;


import com.alkemy.cysjava.virtualwallet.repositories.AccountRepository;
import com.alkemy.cysjava.virtualwallet.repositories.RoleRepository;
import com.alkemy.cysjava.virtualwallet.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.context.ApplicationContext;



import java.sql.Timestamp;

import java.util.ArrayList;

import java.util.List;

import java.util.Optional;



@SpringBootApplication

public class CySJavaVirtualWalletApplication {



	public static void main(String[] args) {

		SpringApplication.run(CySJavaVirtualWalletApplication.class, args);

	}

}

