/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectog;

/**
 *
 * @author alber
 */
public class MathUtil {
    public static float deg2Rad(float deg){
		while(deg > 360.0){
			deg =- 360;
		}
		return (float) (deg * Math.PI / 180.0); 
	}
}
