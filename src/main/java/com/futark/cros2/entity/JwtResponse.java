package com.futark.cros2.entity;

public class JwtResponse {

    private Utilisateur user;
    private String jwtToken;

    public JwtResponse(Utilisateur user, String jwtToken) {
        this.user = user;
        this.jwtToken = jwtToken;
    }

    public Utilisateur getUser() {
        return user;
    }

    public void setUser(Utilisateur user) {
        this.user = user;
    }

    public String getJwtToken() {
        return jwtToken;
    }

    public void setJwtToken(String jwtToken) {
        this.jwtToken = jwtToken;
    }
}
