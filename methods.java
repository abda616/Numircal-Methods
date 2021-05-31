/*
 * Copyright (c) 2021.
 * ABDEL RAHMAN ALHERBAWi.
 * Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 * All Rights reserved.
 */
package NUM;

import java.math.BigDecimal;
import java.util.ArrayList;

public class methods {
    static int dic;
    static int num_of_it = 0;
    static String out;
    /** To find the value of the output compensation within the equation */
    public static double fx(String str, double x) {
        String finalStr = str.replaceAll("x", String.valueOf(x));
        return new Object() {
            int pos = -1, ch;

            void nextChar() {
                ch = (++pos < finalStr.length()) ? finalStr.charAt(pos) : -1;
            }

            boolean eat(int charToEat) {
                while (ch == ' ') nextChar();
                if (ch == charToEat) {
                    nextChar();
                    return true;
                }
                return false;
            }

            double parse() {
                nextChar();
                double x = parseExpression();
                if (pos < finalStr.length()) throw new RuntimeException("Unexpected: " + (char) ch);
                return truncateDecimal(x);
            }

            double parseExpression() {
                double x = parseTerm();
                for (; ; ) {
                    if (eat('+')) x += parseTerm(); // addition
                    else if (eat('-')) x -= parseTerm(); // subtraction
                    else return x;
                }
            }

            double parseTerm() {
                double x = parseFactor();
                for (; ; ) {
                    if (eat('*')) x *= parseFactor(); // multiplication
                    else if (eat('/')) x /= parseFactor(); // division
                    else return x;
                }
            }

            double parseFactor() {
                if (eat('+')) return parseFactor(); // unary plus
                if (eat('-')) return -parseFactor(); // unary minus
                double x;
                int startPos = this.pos;
                if (eat('(')) { // parentheses
                    x = parseExpression();
                    eat(')');
                } else if ((ch >= '0' && ch <= '9') || ch == '.') {
                    while ((ch >= '0' && ch <= '9') || ch == '.') nextChar();
                    x = Double.parseDouble(finalStr.substring(startPos, this.pos));
                } else if (ch >= 'a' && ch <= 'z') {
                    while (ch >= 'a' && ch <= 'z') nextChar();
                    String func = finalStr.substring(startPos, this.pos);
                    x = parseFactor();
                    if (func.equals("sqrt")) x = Math.sqrt(x);
                    else if (func.equals("sin")) x = Math.sin(Math.toRadians(x));
                    else if (func.equals("cos")) x = Math.cos(Math.toRadians(x));
                    else if (func.equals("tan")) x = Math.tan(Math.toRadians(x));
                    else throw new RuntimeException("Unknown function: " + func);
                } else {
                    throw new RuntimeException("Unexpected: " + (char) ch);
                }
                if (eat('^')) x = Math.pow(x, parseFactor());
                return x;
            }
        }.parse();
    }
    /**THIS method  TO find error between ( current and last  ) or (true value and approximation )*/
    public static double error(double cur, double prv) {
        if(cur!=0) return truncateDecimal(Math.abs(((cur - prv) / cur) * 100));
        else return 0; }
    /** THIS METHOD TO CHOPPING THE NUMBER BY NUMBER OF DIGITS U ENTER */
    public static double truncateDecimal(double x) {
        return new BigDecimal(String.valueOf(x)).setScale(dic,BigDecimal.ROUND_FLOOR).doubleValue(); }


    /***/
    protected static String Bic_met(String equation, double a, double b, int max_num, double EP,
                                    ArrayList<Double> arr_root, ArrayList<Double> ero_arr, ArrayList<Integer> num,
                                    ArrayList<Double> upper, ArrayList<Double> lower) {
        if (fx(equation, a) * fx(equation, b) < 0) {
            while (num_of_it < max_num) {
                if (num_of_it > 1)
                    if (ero_arr.get(num_of_it-1) <= EP) {
                        out= "Done";
                        break;
                    }
                num.add(num_of_it);
                ///////////////////
                upper.add(b);
                lower.add(a);
                double root = bic_root(a, b);
                arr_root.add(root);
                if (num_of_it >= 1) {
                    ero_arr.add(error(arr_root.get(num_of_it), arr_root.get(num_of_it - 1)));
                }
                if (fx(equation, root) == 0.0) {
                    out= "Done";
                    break;
                } else if (fx(equation, a) * fx(equation, root) < 0) {
                    b = root;
                    ++num_of_it;
                    return Bic_met(equation, a, b, max_num, EP, arr_root, ero_arr, num, upper, lower);
                } else if (fx(equation, root) * fx(equation, b) < 0) {
                    a = root;
                    ++num_of_it;
                    return Bic_met(equation, a, b, max_num, EP, arr_root, ero_arr, num, upper, lower);
                } else out= "Not Allowed";
            }
        } else out= "Not Allowed";
        return out;
    }
    private static double bic_root(double a, double b) { return truncateDecimal((a + b) / 2); }

    /***/
    protected static String false_met(String equation, double a, double b,
                                      int max_num, double EP, ArrayList<Double> arr_root,
                                      ArrayList<Double> ero_arr, ArrayList<Integer> num, ArrayList<Double> upper, ArrayList<Double> lower) {
        if (fx(equation, a) * fx(equation, b) < 0) {

            while (num_of_it < max_num) {
                if (num_of_it > 1)
                    if (ero_arr.get(num_of_it - 1) <= EP) {
                        out= "Done";
                        break;
                    }
                num.add(num_of_it);
                upper.add(b);
                lower.add(a);

                double root = false_po_root(equation, a, b);
                arr_root.add(root);
                if (num_of_it >= 1) {
                    ero_arr.add(error(arr_root.get(num_of_it), arr_root.get(num_of_it - 1)));
                }
                if (fx(equation, root) == 0.0) {
                    out= "Done";
                    break;
                } else if (fx(equation, a) * fx(equation, root) < 0) {
                    b = root;
                    ++num_of_it;
                    return false_met(equation, a, b, max_num, EP, arr_root, ero_arr, num, upper, lower);
                } else if (fx(equation, root) * fx(equation, b) < 0) {
                    a = root;
                    ++num_of_it;
                    return false_met(equation, a, b, max_num, EP, arr_root, ero_arr, num, upper, lower);
                } else out= "Not Allowed";
            }
        } else out= "Not Allowed";
        return out;
    }
    private static double false_po_root(String equation, double a, double b) {
        return truncateDecimal(b - ((fx(equation, b) * (a - b)) / (fx(equation, a) - fx(equation, b)))); }
    /***/
    protected static String Fixed_point_met(String equation, String eq_gx, double x, double EP, int max_num,
                                            ArrayList<Integer> num, ArrayList<Double> arr_root, ArrayList<Double> ero_arr) {
        while(num_of_it < max_num) {
            if (num_of_it > 1)
                if (ero_arr.get(num_of_it - 1) <= EP) {
                    out= "Done";break;
                }
            num.add(num_of_it);
            arr_root.add(x);
            double root = truncateDecimal(fx(eq_gx, x));
            if (num_of_it >= 1)
                ero_arr.add(error(arr_root.get(num_of_it), arr_root.get(num_of_it - 1)));
            if (fx(equation, root) == 0.0) { out= "Done";break;
            } else {
                ++num_of_it;
                 try{ return  Fixed_point_met(equation, eq_gx, root, EP, max_num, num, arr_root, ero_arr); }catch (Exception e){
                     out = "Divergence";
                 }
            }
        }
        return out;
    }
    /***/
    protected static String Newton_met(String equation,String eq_gx, double x, double EP,int max_num,
                                       ArrayList<Integer> num,ArrayList<Double> cur_root, ArrayList<Double> arr_root, ArrayList<Double> ero_arr) {
        while(num_of_it < max_num) {
            if (num_of_it > 1)
                if (ero_arr.get(num_of_it-1) <= EP) {
                    out= "Done";break;
                }
            num.add(num_of_it);
            cur_root.add(x);
            double root = newton_root(equation,eq_gx,x);
            arr_root.add(root);
            if (num_of_it >= 1)
                ero_arr.add( error( arr_root.get(num_of_it), arr_root.get(num_of_it - 1) ) );
            if (fx(equation, root) == 0.0) {
                out="Done";break;
            } else {
                ++num_of_it;
                try{ return  Newton_met(equation, eq_gx, root, EP, max_num, num, cur_root, arr_root, ero_arr); }
                catch (Exception e){
                    out = "Divergence";
                }
            }
        }
        return out;
    }
    private static double newton_root(String equation,String eq_gx, double x) {
        return truncateDecimal( x-(fx(equation,x)/fx(eq_gx, x))); }
    /***/
    protected static String Secant_met(String equation, double iLast,double iCur, double EP
            , int max_num, ArrayList<Integer> num, ArrayList<Double> IC, ArrayList<Double> IL, ArrayList<Double> NRoot, ArrayList<Double> ero_arr)  {
        while(num_of_it < max_num) {
            if (num_of_it > 1)
                if (ero_arr.get(num_of_it - 1) <= EP) {
                    out= "Done";break;
                }

            num.add(num_of_it);
            IC.add(iCur);
            IL.add(iLast);

            double root = secant_root(equation, iCur, iLast);
            NRoot.add(root);

            if (num_of_it >= 1)
                ero_arr.add(error(NRoot.get(num_of_it), NRoot.get(num_of_it - 1)));

            if (fx(equation, root) == 0.0) {
                out= "Done";break;
            }else {
                ++num_of_it;
                try{ return Secant_met(equation,iCur,root,EP,max_num,num, IC, IL, NRoot,ero_arr); }
                catch (Exception e){
                    out = "Divergence";
                }
            }
        }
        return out;
    }
    private static double secant_root(String equation ,double a,double b) {
        return truncateDecimal( a-(fx(equation,a)*( (a-b) / (fx(equation, a) - fx(equation, b)) ) ) );
    }

}