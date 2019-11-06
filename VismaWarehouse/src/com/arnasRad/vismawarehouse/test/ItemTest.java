package com.arnasRad.vismawarehouse.test;

import com.arnasRad.vismawarehouse.model.Item;
import org.junit.Assert;
import org.junit.Test;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class ItemTest {

     @Test
     public void ItemsEqual() {

          // Arrange
          Calendar calendar = new GregorianCalendar(1990, Calendar.JANUARY, 31);
          Item item1 = new Item("123", "testName", calendar.getTime());
          Item item2 = new Item("123", "testName", calendar.getTime());

          // Act
          boolean result = item1.equals(item2);

          // Assert
          Assert.assertTrue(result);
     }

     @Test
     public void ItemsEqual1() {

          // Arrange
          Calendar calendar = new GregorianCalendar(1990, Calendar.JANUARY, 31);
          Item item1 = new Item("123", "testName", calendar.getTime());
          Item item2 = new Item("123", "testName", calendar.getTime());

          // Act
          boolean result = item1.equals(item2);

          // Assert
          Assert.assertTrue(result);
     }

     @Test
     public void ItemsNotEqualCode() {

          // Arrange
          Calendar calendar = new GregorianCalendar(1990, Calendar.JANUARY, 31);
          Item item1 = new Item("123", "testName", calendar.getTime());
          Item item2 = new Item("1234", "testName", calendar.getTime());

          // Act
          boolean result = item1.equals(item2);

          // Assert
          Assert.assertFalse(result);
     }

     @Test
     public void ItemsNotEqualName() {

          // Arrange
          Calendar calendar = new GregorianCalendar(1990, Calendar.JANUARY, 31);
          Item item1 = new Item("123", "testName", calendar.getTime());
          Item item2 = new Item("123", "testNames", calendar.getTime());

          // Act
          boolean result = item1.equals(item2);

          // Assert
          Assert.assertFalse(result);
     }

     @Test
     public void ItemsNotEqualDate() {

          // Arrange
          Calendar calendar = new GregorianCalendar(1990, Calendar.JANUARY, 31);
          Item item1 = new Item("123", "testName", calendar.getTime());
          calendar.add(Calendar.DATE, 1);
          Item item2 = new Item("123", "testName", calendar.getTime());

          // Act
          boolean result = item1.equals(item2);

          // Assert
          Assert.assertFalse(result);
     }
}
