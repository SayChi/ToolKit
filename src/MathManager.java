// As created by Bastiaan Wuisman and Laurence Keijzer on 20-7-2015
// Created using IntelliJ IDEA

import java.awt.*;
import java.awt.geom.Line2D;

import static java.lang.Math.*;

public class MathManager {

	//TODO: change Line2D

	double radToDeg( double rad ) {
		return (rad * 360) / (2 * PI);
	}

	double degToRad( double deg ) {
		return (deg * 2 * PI) / 360;
	}

	double minRad( double rad ) {
		return mod(rad, 2 * PI);
	}

	double minDeg( double deg ) {
		return mod(deg, 360);
	}

	double mod( double x, double base ) {
		return x - (floor(x / base) * base);
	}

	//TODO
	double angle( Point p1, Point p2, Point m ) {
		return 0;
	}

	//TODO
	double angle( Line2D l1, Line2D l2 ) {
		return 0;
	}

	//TODO
	void rotateLine( Line2D l, double rad ) {

	}

	double length( Point p1, Point p2 ) {
		return sqrt(pow(p1.x - p2.x, 2) + pow(p1.y - p2.y, 2));
	}

	double length( Line2D l1 ) {
		return sqrt(pow(l1.getX1() - l1.getX2(), 2) + pow(l1.getY1() - l1.getY2(), 2));
	}

	//USE ONLY WITH MAXIMUM BASE 10
	int baseConv( int x, int base, int targetBase ) {
		int[] formatConverted = new int[(int) log10(x) + 1];
		for( int i = 0; i < formatConverted.length; i++ ) {
			formatConverted[i] = (int) mod(x / pow(10, i), 10);
			if( formatConverted[i] >= base ) return -1;
		}

		int[] result = baseConv(formatConverted, base, targetBase);
		int formatConvertedBack = 0;
		for( int i = 0; i < result.length; i++ ) {
			formatConvertedBack += result[i] * pow(10, i);
		}

		return formatConvertedBack;
	}

	int[] baseConv( int[] x, int base, int targetBase ) {
		int base10 = 0;
		for( int i = 0; i < x.length; i++ ) {
			base10 += pow(base, i) * x[i];
		}

		int length = (int) ceil(log(base10 + 1) / log(targetBase));
		int[] y = new int[length];
		for( int i = y.length - 1; i >= 0; i-- ) {
			int number = targetBase - 1;
			while( number >= 0 ) {
				if( number * pow(targetBase, i) <= base10 ) {
					base10 -= number * pow(targetBase, i);
					y[i] = number;
					break;
				}
				number--;
			}
		}
		return y;
	}
}