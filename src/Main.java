
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.TextCodec;
import java.io.UnsupportedEncodingException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author e197504
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws UnsupportedEncodingException {
        // TODO code application logic here
        
        String jwtStr = Jwts.builder()
	.claim("username", "diego")
	.claim("atributo", "este es un atributo de prueba")
	.signWith(
		SignatureAlgorithm.HS256,
		TextCodec.BASE64.decode(
			// This generated signing key is
			// the proper length for the
			// HS256 algorithm.
			"O7opWmgnE3wKiw2MgUc1A0R8/Acwwk691SEtakXEhzM="
		)
	)
	.compact();
        
        System.out.println("El JWT generado es: " + jwtStr);
        
        System.out.println();
        System.out.println("Extrayendo atributos... ");
        
        Jws<Claims> jws = Jwts.parser()
	.require("username", "diego")
	.require("atributo", "este es un atributo de prueba")
	.setSigningKey(
		TextCodec.BASE64.decode(
			"O7opWmgnE3wKiw2MgUc1A0R8/Acwwk691SEtakXEhzM="
		)
	)
	.parseClaimsJws(jwtStr);
        
        System.out.println("JWT Header: " + jws.getHeader());
        System.out.println("JWT Body: " + jws.getBody());
        
        System.out.println("Linea con cambio");
    }
    
    

}
