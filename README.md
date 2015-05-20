# ApacheJMeter_joda 0.0.1b
JMeter Functions wrapping joda-time functionality 


*   __\_\_DayOfWeek__: Returns the day of the week (MONDAY,TUESDAY,WEDNESDAY,THURSDAY,FRIDAY,SATURDAY,SUNDAY) of the passed date.

    Usage:
    
        ${__DayOfWeek(2015-01-01)}
        ${__DayOfWeek(${__time(yyyy-MM-dd)})}
        
*   __\_\_GenerateDateRange__: Creates JMETER variables with the date values from 'fromDate' to 'toDate', the variable names are created 
    as ${prefix} + counter where ${prefix} is the third argument passed to this function and counter always start from 0. 

    Usage:
        
        ${__GenerateDateRange(${__time(yyyy-MM-dd)},${__PlusDays(${__time(yyyy-MM-dd)},20)},prefix_,WEDNESDAY,FRIDAY)}
        ${__GenerateDateRange(${__time(yyyy-MM-dd)},${__PlusDays(${__time(yyyy-MM-dd)},20)},prefix_,WEDNESDAY)}
        ${__GenerateDateRange(${__time(yyyy-MM-dd)},${__PlusDays(${__time(yyyy-MM-dd)},20)},prefix_)}
*   __\_\_MinusDays__: Subtract N number of days from the specified date.

    Usage:
        
        ${__MinusDays(${__time(yyyy-MM-dd)},20)}
        ${__MinusDays(2015-01-01,20)}
*   __\_\_MinusMonths__: Subtract N number of months from the specified date.
                       
    Usage:

        ${__MinusMonths(${__time(yyyy-MM-dd)},1)}
        ${__MinusMonths(2015-01-01,2)}
*   __\_\_MinusWeeks__: Subtract N number of weeks from the specified date.
                                             
    Usage:
    
        ${__MinusWeeks(${__time(yyyy-MM-dd)},1)}
        ${__MinusWeeks(2015-01-01,3)}
*   __\_\_MinusYears__: Subtract N number of years from the specified date.
                                                                   
    Usage:
    
        ${__MinusYears(${__time(yyyy-MM-dd)},1)}
        ${__MinusYears(2015-01-01,3)}
*   __\_\_MonthOfYear__: Returns name of the month of the passed date.
                                                                                          
    Usage: 
    
        ${__MonthOfYear(${__time(yyyy-MM-dd)})}
        ${__MonthOfYear(2015-01-01)}
*   __\_\_PlusDays__: Add N number of days to the specified date.
                      
    Usage:

        ${__PlusDays(${__time(yyyy-MM-dd)},20)}
        ${__PlusDays(2015-01-01,20)}
*   __\_\_PlusMonths__: Add N number of months to the specified date.
                                            
    Usage:
    
        ${__PlusMonths(${__time(yyyy-MM-dd)},1)}
        ${__PlusMonths(2015-01-01,3)}
*   __\_\_PlusWeeks__: Add N number of weeks to the specified date.
                                                                 
    Usage:
    
        ${__PlusWeeks(${__time(yyyy-MM-dd)},1)}
        ${__PlusWeeks(2015-01-01,5)}
*   __\_\_PlusYears__: Add N number of years to the specified date.
                                                                                      
    Usage:
    
        ${__PlusYears(${__time(yyyy-MM-dd)},1)}
        ${__PlusYears(2015-01-01,2)}