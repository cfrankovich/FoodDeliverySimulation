// ******************************************************
// Class:			Clock
// Name:			Carson Frankovich
// Date:			2022-12-13
//
// Purpose:			Custom time like object 
//
// Attributes:		-startTime: int
//					-endTime: int
//					-durationInMins: int
//					
// Methods:			+setStartTime( int ): void
//					+setEndTime( int ): void
//					+getStartTime( ): int
//					+getEndTime( ): int
//					+calculateDuration( ): void
//					+getDurationInMins( ): int
//					+getDurationInHours( ): int
//					+minutesToTime( int ): int
//					
// ******************************************************

package dev.frankovich.util;

public class Clock 
{
    private int startTime;
    private int endTime;
    private int durationInMins;    

    public Clock()
    {

    }

    public Clock(int startTime, int endTime)
    {
        this.startTime = startTime;
        this.endTime = endTime;
        durationInMins = (endTime - startTime) * 60;
    }

    public void setStartTime(int time)
    {
        startTime = time;
    }

    public void setEndTime(int time)
    {
        endTime = time;
    }

    public int getStartTime()
    {
        return startTime;
    }

    public int getEndTime()
    {
        return endTime;
    }

    public void calculateDuration()
    {
        durationInMins = (endTime - startTime) * 60;
    }

    public int getDurationInMins()
    {
        return durationInMins;
    }

    public int getDurationInHours()
    {
        return Math.round(durationInMins/60);
    }

    public int minutesToTime(int mins)
    {
        return 0;
    }
}
