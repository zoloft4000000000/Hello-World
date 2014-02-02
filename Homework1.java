/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package homework1;

import javax.swing.JOptionPane;

/**
 *
 * @author Rodolfo Marrero
 * @author Wilfredo Galdamez
 */
public class Homework1
{

    //creates a method for telling the user that the input is not a proper number
    public static void tryAgain()
    {
        JOptionPane.showMessageDialog(null, "Enter a valid amount and try again");
        System.exit(0);
    }
    
    public static void handleNull(String value)
    {
        if ( value == null )
        {
            JOptionPane.showMessageDialog(null, "You clicked on cancel. Exiting program...");
            System.exit(0);
        }
    }
    
    public static void handleEmpty(String value)
    {
        if ( value.isEmpty() )
        {
            tryAgain();
        } 
    }

    /**
    * Checks if the characters are digits or decimals
    * Entering just decimal "." would normally be allowed, but now it is
    * not important; This checks the purchase amount
    */
    public static void checkIfDouble(String value)
    {
        /*if ( value.contains(".") )
        {
            tryAgain();
        }*/
        for( int i = 0; i < value.length(); i++ )
        {
            if (!Character.isDigit(value.charAt(i)) && Character.valueOf(value.charAt(i)) != '.' ) {
                tryAgain();
            }
        }
        
        boolean decimalPoint = false;
        for (int i = 0; i < value.length(); i = i + 1) {
            if (value.charAt(i) == '.') {
                if (!decimalPoint) {
                    decimalPoint = true;
                } else {
                    tryAgain();
                }
                if (value.length() == 1 && value.charAt(i) == '.') {
                    tryAgain();
                }

            }
        }
    }

    public static String getResult(double change, double value, String result)
    {
        if ( change >= value )
        {
            change = Math.round(change * 100);
            value = Math.round(value * 100);
            Double value_is_in_change_tmp = change / value;
            value = value / 100;
            int value_is_in_change = value_is_in_change_tmp.intValue();

            String identifier = "";
            if ( value == 20 )
            {
                identifier = "Twenty Dollar Bill(s)";
            }
            else if ( value == 10 )
            {
                identifier = "Ten Dollar Bill(s)";
            }
            else if ( value == 5 )
            {
                identifier = "Five Dollar Bill(s)";
            }
            else if ( value == 1 )
            {
                identifier = "One Dollar Bill(s)";
            }
            else if ( value == 0.25 )
            {
                identifier = "Quarter(s)";
            }
            else if ( value == 0.1 )
            {
                identifier = "Dime(s)";
            }
            else if ( value == 0.05 )
            {
                identifier = "Nickel(s)";
            }
            else
            {
                identifier = "Penny(s)";
            }
            result = result + " " + value_is_in_change + " " + identifier + "\n";
            //System.out.println(value_is_in_change + " -> $" + value + " " + identifier);
        }
        return result;
    }

    public static double getChange(double change, double value)
    {
        if ( change >= value )
        {
            change = Math.round(change * 100);
            value = Math.round(value * 100);
            change = change % value;
            change = change / 100;
            //System.out.println("Change: " + change );
        }
        return change;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        /**
         * Set up variables
         */
        String result = "";
        double double_purchase, double_integer, total_change, partial_change;
        double[] bills_allowed = new double[]{20,10,5,1,0.25,0.1,0.05,0.01};

        String purchase = JOptionPane.showInputDialog("Please enter the purchase amount");

        handleNull(purchase);
        handleEmpty(purchase);
        checkIfDouble(purchase);

        String given = JOptionPane.showInputDialog("Please enter the given amount");

        handleNull(given);
        handleEmpty(given);
        checkIfDouble(given);

        double_purchase = Double.parseDouble(purchase);
        double_integer = Double.parseDouble(given);
        double_purchase = Math.round(double_purchase * 100);
        double_integer = Math.round(double_integer * 100);
        total_change = double_integer - double_purchase;
        total_change = total_change/100;

        if ( total_change < 0 )
        {
            JOptionPane.showMessageDialog(null, "Give the cashier more money!");
            System.exit(0);
        }
        else if ( total_change == 0 )
        {
            JOptionPane.showMessageDialog(null, "No change needed");
            System.exit(0);
        }

        //System.out.println("Total Change: $" + total_change);

        partial_change = total_change;
        for( int i = 0; i < bills_allowed.length; i++ )
        {
            result = getResult(partial_change, bills_allowed[i], result);
            partial_change = getChange(partial_change, bills_allowed[i]);
        }

        JOptionPane.showMessageDialog(null, "The total is: $" + total_change + "\nAmount to give is: \n" + result);
    }

}