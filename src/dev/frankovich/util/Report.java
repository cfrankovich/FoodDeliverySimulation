// ******************************************************
// Class:			Report
// Name:			Carson Frankovich
// Date:			2022-12-13
//
// Purpose:			Generates report at the end
//
// Attributes:		
// Methods:			+exportCSV( SimulationController, String ): void
//					
// ******************************************************

package dev.frankovich.util;

import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;

import dev.frankovich.sim.Driver;
import dev.frankovich.sim.MenuItem;
import dev.frankovich.sim.Order;
import dev.frankovich.sim.SimulationController;

public class Report 
{
    public void exportCSV(SimulationController controller, String fileName)
    {
        /* Get Data */
        ArrayList<String> orderInfo = new ArrayList<String>();
        double averageDeliveryTime = 0;
        double revenue = 0;
        double profit = 0;
        double totalMiles = 0;
        for (Driver d : controller.getDriverList())
        {
            totalMiles += d.getGasUsage();
        }
        double driverPay = (Utils.clock.getDurationInMins() * (Utils.driverSalary/60));

        String builder = "";
        for (Order order : Utils.archivedOrders)
        {
            builder += order.getID() + ",";

            String miniBuilder = "";
            for (MenuItem item : order.getItems())
            {
                miniBuilder += item.getName() + "+";
            }
            miniBuilder = miniBuilder.substring(0, miniBuilder.length()-1);
            builder += miniBuilder + ",";

            builder += order.getPrice() + ",";
            revenue += order.getPrice();

            builder += order.getTimeOrdered() + ","; 

            builder += order.getTimeReady() + ",";

            builder += order.getTimePickedUp() + ",";

            builder += order.getTimeDelivered() + ",";

            builder += order.getTotalTime() + "\n";
            averageDeliveryTime += order.getTotalTime();
        }
        
        /* Build Strings */
        String openingLines[] = new String[17];
        openingLines[0] = "Order Frequency," + Utils.orderFrequency;
        openingLines[1] = "";
        openingLines[2] = "Price Per Gallon," + Utils.USDperGallonGas;
        openingLines[3] = "Driver Salary," + Utils.driverSalary;
        openingLines[4] = "Num Drivers," + Utils.numDrivers;
        openingLines[5] = "";
        openingLines[6] = "Start Time," + Utils.clock.getStartTime();
        openingLines[7] = "End Time," + Utils.clock.getEndTime();
        openingLines[8] = "Duration," + Utils.clock.getDurationInMins();
        openingLines[9] = "";
        openingLines[10] = "Average Delivery Time," + averageDeliveryTime/Utils.archivedOrders.size();
        openingLines[11] = "Total Miles," + totalMiles;
        openingLines[12] = "Revenue," + revenue;
        openingLines[13] = "Driver Pay," + driverPay; 
        openingLines[14] = "Profit," + (revenue-driverPay);
        openingLines[15] = "";
        openingLines[16] = "Order #,Items,Total Price,Ordered,Ready,Picked Up,Delivered,Total Time";

        /* File Export */
        try
        {
            PrintWriter output = new PrintWriter(new FileOutputStream(fileName, false));
            for (String s : openingLines)
            {
                output.write(s + "\n");
            }
            output.write(builder);
            output.close();
            System.out.println("Sucessfully exported report file to \"" + fileName + "\"");
        }
        catch (Exception e)
        {
            System.out.println("Error writing to export file.");
            System.exit(0);
        }
        

    }
    
}
