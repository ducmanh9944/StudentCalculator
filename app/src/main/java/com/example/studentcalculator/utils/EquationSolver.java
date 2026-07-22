package com.example.studentcalculator.utils;

public class EquationSolver {

    public static String solveLinear(double a, double b) {
        if (a == 0) {
            return (b == 0) ? "Vô số nghiệm" : "Vô nghiệm";
        }
        return "x = " + NumberFormatter.format(-b / a);
    }

    public static String solveQuadratic(double a, double b, double c) {
        if (a == 0) return solveLinear(b, c);
        double delta = b * b - 4 * a * c;
        if (delta < 0) return "Vô nghiệm";
        if (delta == 0) return "Nghiệm kép x = " + NumberFormatter.format(-b / (2 * a));
        double x1 = (-b + Math.sqrt(delta)) / (2 * a);
        double x2 = (-b - Math.sqrt(delta)) / (2 * a);
        return "x1 = " + NumberFormatter.format(x1) + "\nx2 = " + NumberFormatter.format(x2);
    }

    public static String solveCubic(double a, double b, double c, double d) {
        if (a == 0) return solveQuadratic(b, c, d);

        // Chuyển về dạng x^3 + ax^2 + bx + c = 0
        double a1 = b / a, a2 = c / a, a3 = d / a;
        double Q = (3 * a2 - a1 * a1) / 9;
        double R = (9 * a1 * a2 - 27 * a3 - 2 * Math.pow(a1, 3)) / 54;
        double D = Math.pow(Q, 3) + Math.pow(R, 2);

        if (D > 0) {
            double S = Math.cbrt(R + Math.sqrt(D));
            double T = Math.cbrt(R - Math.sqrt(D));
            double x1 = -a1 / 3 + (S + T);
            return "1 nghiệm thực:\nx = " + NumberFormatter.format(x1);
        } else if (D == 0) {
            double S = Math.cbrt(R);
            double x1 = -a1 / 3 + 2 * S;
            double x2 = -a1 / 3 - S;
            return "Nghiệm bội:\nx1 = " + NumberFormatter.format(x1) + "\nx2 = x3 = " + NumberFormatter.format(x2);
        } else {
            double theta = Math.acos(R / Math.sqrt(-Math.pow(Q, 3)));
            double x1 = 2 * Math.sqrt(-Q) * Math.cos(theta / 3) - a1 / 3;
            double x2 = 2 * Math.sqrt(-Q) * Math.cos((theta + 2 * Math.PI) / 3) - a1 / 3;
            double x3 = 2 * Math.sqrt(-Q) * Math.cos((theta + 4 * Math.PI) / 3) - a1 / 3;
            return "3 nghiệm thực:\nx1 = " + NumberFormatter.format(x1) + "\nx2 = " + NumberFormatter.format(x2) + "\nx3 = " + NumberFormatter.format(x3);
        }
    }

    public static String solveSystem(double a1, double b1, double c1, double a2, double b2, double c2) {
        double D = a1 * b2 - a2 * b1;
        if (D == 0) {
            return (c1 * b2 - c2 * b1 == 0) ? "Vô số nghiệm" : "Vô nghiệm";
        }
        return "x = " + NumberFormatter.format((c1 * b2 - c2 * b1) / D) + 
               "\ny = " + NumberFormatter.format((a1 * c2 - a2 * c1) / D);
    }
}