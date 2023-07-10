package br.com.compassuol.pb.challenge.authorization.configs;

public class JwtResponse {
    private final String token;

    public JwtResponse(String token) {
        this.token = token;
    }

    // getter

    public String getToken() {
        return token;
    }
    
}