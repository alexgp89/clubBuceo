/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.clubbuceo.enums;

/**
 *
 * @author Alejandro
 */
public enum Titulacion {
    OpenWater, Advance, Rescue, Otro;
    
    @Override
    public String toString() {
        return name(); // ya son legibles tal cual
    }
}
