package Codes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MyGenListTest {
    MyGenList<Integer> listInt;
    MyGenList<Cat> listCat;

    @BeforeEach
    void setUp() {
        listInt = new MyGenList<Integer>();
        listCat = new MyGenList<Cat>();
    }

    @Test
    void testAddValid1() {
        assertDoesNotThrow(() -> {
            listInt.add(10);
            listInt.add(20);
            listInt.add(30);
        });
        assertEquals(3, listInt.size());
    }

    @Test
    void testAddValid2() {
        assertDoesNotThrow(() -> {
            listInt.add(10);
            listInt.add(20);
            listInt.add(30);
            listInt.add(40);
            listInt.add(50);
        });
        assertEquals(5, listInt.size());
    }
    @Test
    void testAddAtValid1() {
        assertDoesNotThrow(() -> {
            listInt.add(20);
            listInt.add(40);
            listInt.add(50);
            listInt.addAt(2, 30);
            listInt.addAt(1, 10);
        });
        assertEquals(5, listInt.size());
        assertEquals(40, listInt.get(4));
        assertEquals(50, listInt.get(5));
    }

    @Test
    void testAddAtValid2() {
        assertDoesNotThrow(() -> {
            listInt.addAt(1, 10);
            listInt.add(20);
            listInt.add(30);
            listInt.addAt(1, 5);
            listInt.addAt(5, 50);
        });
        assertEquals(5, listInt.size());
        assertEquals(30, listInt.get(4));
    }

    @Test
    void testAddAtInvalid1() {
        assertDoesNotThrow(() -> {
            listInt.addAt(1, 10);
            listInt.add(20);
            listInt.add(30);
            listInt.addAt(1, 5);
            listInt.addAt(5, 50);
        });
        Exception e = assertThrowsExactly(ArrayFullException.class, ()->{
            listInt.addAt(2, 8);});
        assertEquals("The array is full and 8 cannot be inserted.", e.getMessage());
        assertEquals(5, listInt.size());
        assertEquals(30, listInt.get(4));
    }

    @Test
    void testAddAtInvalid2() {
        assertDoesNotThrow(() -> {
            listInt.add(10);
            listInt.addAt(2, 20);
            listInt.addAt(1, 5);
        });
        Exception e = assertThrowsExactly(InvalidPositionException.class, ()->{
            listInt.addAt(5, 100);});
        assertEquals("Position must be between 1 and 4", e.getMessage());
        assertEquals(3, listInt.size());
    }

    @Test
    void testAddAtInvalid3() {
        Exception e = assertThrowsExactly(InvalidPositionException.class, ()->{
            listInt.addAt(2, 100);});
        assertEquals("Position must be between 1 and 1", e.getMessage());
        assertEquals(0, listInt.size());
    }

    @Test
    void testAddInvalid1() {
        assertDoesNotThrow(() -> {
            listInt.add(10);
            listInt.add(20);
            listInt.add(30);
            listInt.add(40);
            listInt.add(50);
        });
        ArrayFullException e1 = assertThrowsExactly(ArrayFullException.class, () -> {
            listInt.add(60);
        });
        Exception e2 = assertThrowsExactly(ArrayFullException.class, () -> {
            listInt.add(70);
        });
        assertEquals(5, listInt.size());
        assertEquals("The array is full and 60 cannot be inserted.", e1.getMessage());
        assertEquals("The array is full and 70 cannot be inserted.", e2.getMessage());
        assertFalse(e2 instanceof RuntimeException);
    }

    @Test
    void testRemoveTrue() {
        assertDoesNotThrow(() -> {
            listInt.add(10);
            listInt.add(20);
            listInt.add(30);
            listInt.add(40);
            listInt.add(50);
        });
        assertTrue(listInt.remove(50));
        assertTrue(listInt.remove(20));
        assertTrue(listInt.remove(40));
    }

    @Test
    void testRemoveFalse1() {
        assertDoesNotThrow(() -> {
            listInt.add(10);
            listInt.add(20);
            listInt.add(30);
            listInt.add(50);
        });
        assertFalse(listInt.remove(2));
        assertFalse(listInt.remove(40));
    }

    @Test
    void testGetValid1() {
        assertDoesNotThrow(() -> {
            listInt.add(10);
            listInt.add(20);
            listInt.add(30);
            listInt.add(40);
            listInt.add(50);
        });
        assertEquals(10, listInt.get(1));
        assertEquals(30, listInt.get(3));
        assertEquals(50, listInt.get(5));
    }

    @Test
    void testGetInvalid1() {
        assertDoesNotThrow(() -> {
            listInt.add(10);
            listInt.add(20);
            listInt.add(30);
        });
        assertEquals(10, listInt.get(1));
        assertEquals(30, listInt.get(3));
        Exception e = assertThrowsExactly(InvalidPositionException.class, ()->{
            listInt.get(4);});
        assertEquals("Position must be between 1 and 3", e.getMessage());
        assertTrue(e instanceof RuntimeException);
    }

    @Test
    void testGetInvalid2() {
        assertDoesNotThrow(() -> {
            listInt.add(10);
            listInt.add(20);
        });
        assertEquals(10, listInt.get(1));
        Exception e = assertThrowsExactly(InvalidPositionException.class, ()->{
            listInt.get(0);});
        assertEquals("Position must be between 1 and 2", e.getMessage());
    }

    @Test
    void testRemoveFalse2() {
        assertDoesNotThrow(() -> {
            listInt.add(10);
            listInt.add(20);
            listInt.add(30);
            listInt.add(50);
        });
        assertTrue(listInt.remove(20));
        assertFalse(listInt.remove(20));
    }

    @Test
    void testRemoveThenAddContains() {
        assertDoesNotThrow(() -> {
            listInt.add(10);
            listInt.add(20);
            listInt.add(30);
            listInt.add(50);
        });
        assertTrue(listInt.contains(20));
        assertTrue(listInt.remove(20));
        assertFalse(listInt.contains(20));
        assertFalse(listInt.remove(20));
        assertDoesNotThrow(() -> {
            listInt.add(20);});
        assertTrue(listInt.contains(20));
    }

    @Test
    void testRemoveAtValid() {
        assertDoesNotThrow(() -> {
            listInt.add(10);
            listInt.add(20);
            listInt.add(30);
            listInt.add(50);

        });
        assertEquals(4, listInt.size());
        assertEquals(30, listInt.removeAt(3));
        assertEquals(50, listInt.get(3));
        assertEquals(3, listInt.size());
    }

    @Test
    void testRemoveAtInvalid1() {
        assertDoesNotThrow(() -> {
            listInt.add(10);
            listInt.add(20);
            listInt.add(30);

        });
        assertEquals(3, listInt.size());
        Exception e = assertThrowsExactly(InvalidPositionException.class, ()->{
            listInt.removeAt(4);});
        assertEquals("Position must be between 1 and 3", e.getMessage());
        assertEquals(30, listInt.get(3));
        assertEquals(3, listInt.size());
    }

    @Test
    void testIsEmpty() {
        assertTrue(listInt.isEmpty());
        assertDoesNotThrow(() -> {
            listInt.add(13);});
        assertFalse(listInt.isEmpty());
    }

    @Test
    void setValid1() {
        assertDoesNotThrow(() -> {
            listInt.add(10);
            listInt.add(20);
            listInt.add(30);
            assertEquals(20, listInt.set(2, 13));
        });
        assertEquals(13, listInt.get(2));
        assertEquals(3, listInt.size());
    }

    @Test
    void setInvalid1() {
        assertDoesNotThrow(() -> {
            listInt.add(10);
            listInt.add(20);
            listInt.add(30);
        });
        Exception e = assertThrowsExactly(InvalidPositionException.class, ()->{
            listInt.set(4, 50);});
        assertEquals("Position must be between 1 and 3", e.getMessage());
        assertEquals(3, listInt.size());
    }

    @Test
    void cattestAddValid1() {
        assertDoesNotThrow(() -> {
            listCat.add(new Cat("Aries"));
            listCat.add(new Cat("Lily"));
            listCat.add(new Cat("Phoebe"));
        });
        assertEquals(3, listCat.size());
    }

    @Test
    void cattestAddValid2() {
        assertDoesNotThrow(() -> {
            listCat.add(new Cat("Lion"));
            listCat.add(new Cat("Tiger"));
            listCat.add(new Cat("Katol"));
            listCat.add(new Cat("Pooh"));
            listCat.add(new Cat("Pusacat"));
        });
        assertEquals(5, listCat.size());
    }
    @Test
    void cattestAddAtValid1() {
        assertDoesNotThrow(() -> {
            listCat.add(new Cat("Kathniel"));
            listCat.add(new Cat("Kathryn"));
            listCat.add(new Cat("Wow"));
            listCat.addAt(2, new Cat("Omai"));
            listCat.addAt(1, new Cat("Siomeow"));
        });
        assertEquals(5, listCat.size());
        assertEquals("Kathryn", listCat.get(4).name);
        assertEquals("Wow", listCat.get(5).name);
    }

    @Test
    void cattestAddAtValid2() {
        assertDoesNotThrow(() -> {
            listCat.addAt(1, new Cat("Meowy"));
            listCat.add(new Cat("Cathy"));
            listCat.add(new Cat("Furbaby"));
            listCat.addAt(1, new Cat("Kirara"));
            listCat.addAt(5, new Cat("Urie"));
        });
        assertEquals(5, listCat.size());
        assertEquals("Furbaby", listCat.get(4).name);
    }

    @Test
    void cattestAddAtInvalid1() {
        assertDoesNotThrow(() -> {
            listCat.addAt(1, new Cat("Meowy"));
            listCat.add(new Cat("Cathy"));
            listCat.add(new Cat("Furbaby"));
            listCat.addAt(1, new Cat("Kirara"));
            listCat.addAt(5, new Cat("Urie"));
        });
        Exception e = assertThrowsExactly(ArrayFullException.class, ()->{listCat.addAt(2, new Cat("Elle"));});
        assertEquals("The array is full and Elle cannot be inserted.", e.getMessage());
        assertEquals(5, listCat.size());
        assertEquals("Furbaby", listCat.get(4).name);
    }

    @Test
    void cattestAddAtInvalid2() {
        assertDoesNotThrow(() -> {
            listCat.add(new Cat("Meowy"));
            listCat.addAt(2, new Cat("Cathy"));
            listCat.addAt(1, new Cat("Kirara"));
        });
        Exception e = assertThrowsExactly(InvalidPositionException.class, ()->{listCat.addAt(5, new Cat("Lynette"));});
        assertEquals("Position must be between 1 and 4", e.getMessage());
        assertEquals(3, listCat.size());
    }

    @Test
    void cattestAddAtInvalid3() {
        Exception e = assertThrowsExactly(InvalidPositionException.class, ()->{listCat.addAt(2, new Cat("Elle"));});
        assertEquals("Position must be between 1 and 1", e.getMessage());
        assertEquals(0, listCat.size());
    }

    @Test
    void cattestAddInvalid1() {
        assertDoesNotThrow(() -> {
            listCat.add(new Cat("Meowy"));
            listCat.add(new Cat("Cathy"));
            listCat.add(new Cat("Furbaby"));
            listCat.add(new Cat("Kirara"));
            listCat.add(new Cat("Urie"));
        });
        ArrayFullException e1 = assertThrowsExactly(ArrayFullException.class, () -> {
            listCat.add(new Cat("Mrow"));
        });
        Exception e2 = assertThrowsExactly(ArrayFullException.class, () -> {
            listCat.add(new Cat("Fufu"));
        });
        assertEquals(5, listCat.size());
        assertEquals("The array is full and Mrow cannot be inserted.", e1.getMessage());
        assertEquals("The array is full and Fufu cannot be inserted.", e2.getMessage());
        assertFalse(e2 instanceof RuntimeException);
    }

    @Test
    void cattestRemoveTrue() {
        assertDoesNotThrow(() -> {
            listCat.add(new Cat("Meowy"));
            listCat.add(new Cat("Cathy"));
            listCat.add(new Cat("Furbaby"));
            listCat.add(new Cat("Kirara"));
            listCat.add(new Cat("Urie"));
        });
        assertTrue(listCat.remove("Urie"));
        assertTrue(listCat.remove("Cathy"));
        assertTrue(listCat.remove("Kirara"));
    }

    @Test
    void cattestRemoveFalse1() {
        assertDoesNotThrow(() -> {
            listCat.add(new Cat("Meowy"));
            listCat.add(new Cat("Cathy"));
            listCat.add(new Cat("Furbaby"));
            listCat.add(new Cat("Urie"));
        });
        assertFalse(listCat.remove("Furina"));
        assertFalse(listCat.remove("Karina"));
    }

    @Test
    void cattestGetValid1() {
        assertDoesNotThrow(() -> {
            listCat.add(new Cat("Meowy"));
            listCat.add(new Cat("Cathy"));
            listCat.add(new Cat("Furbaby"));
            listCat.add(new Cat("Vince"));
            listCat.add(new Cat("Urie"));
        });
        assertEquals("Meowy", listCat.get(1).name);
        assertEquals("Furbaby", listCat.get(3).name);
        assertEquals("Urie", listCat.get(5).name);
    }

    @Test
    void cattestGetInvalid1() {
        assertDoesNotThrow(() -> {
            listCat.add(new Cat("Meowy"));
            listCat.add(new Cat("Cathy"));
            listCat.add(new Cat("Furbaby"));
        });
        assertEquals("Meowy", listCat.get(1).name);
        assertEquals("Furbaby", listCat.get(3).name);
        Exception e = assertThrowsExactly(InvalidPositionException.class, ()->{listCat.get(4);});
        assertEquals("Position must be between 1 and 3", e.getMessage());
        assertTrue(e instanceof RuntimeException);
    }

    @Test
    void cattestGetInvalid2() {
        assertDoesNotThrow(() -> {
            listCat.add(new Cat("Meowy"));
            listCat.add(new Cat("Cathy"));
        });
        assertEquals("Meowy", listCat.get(1).name);
        Exception e = assertThrowsExactly(InvalidPositionException.class, ()->{listCat.get(0);});
        assertEquals("Position must be between 1 and 2", e.getMessage());
    }

    @Test
    void cattestRemoveFalse2() {
        assertDoesNotThrow(() -> {
            listCat.add(new Cat("Meowy"));
            listCat.add(new Cat("Cathy"));
            listCat.add(new Cat("Furbaby"));
            listCat.add(new Cat("Urie"));
        });
        assertTrue(listCat.remove("Cathy"));
        assertFalse(listCat.remove("Cathy"));
    }

    @Test
    void cattestRemoveThenAddContains() {
        assertDoesNotThrow(() -> {
            listCat.add(new Cat("Meowy"));
            listCat.add(new Cat("Cathy"));
            listCat.add(new Cat("Furbaby"));
            listCat.add(new Cat("Urie"));
        });
        assertTrue(listCat.contains("Cathy"));
        assertTrue(listCat.remove("Cathy"));
        assertFalse(listCat.contains("Cathy"));
        assertFalse(listCat.remove("Cathy"));
        assertDoesNotThrow(() -> {listCat.add(new Cat("Cathy"));});
        assertTrue(listCat.contains("Cathy"));
    }

    @Test
    void cattestRemoveAtValid() {
        assertDoesNotThrow(() -> {
            listCat.add(new Cat("Meowy"));
            listCat.add(new Cat("Cathy"));
            listCat.add(new Cat("Furbaby"));
            listCat.add(new Cat("Urie"));

        });
        assertEquals(4, listCat.size());
        assertEquals("Furbaby", listCat.removeAt(3).name);
        assertEquals("Urie", listCat.get(3).name);
        assertEquals(3, listCat.size());
    }

    @Test
    void cattestRemoveAtInvalid1() {
        assertDoesNotThrow(() -> {
            listCat.add(new Cat("Meowy"));
            listCat.add(new Cat("Cathy"));
            listCat.add(new Cat("Furbaby"));

        });
        assertEquals(3, listCat.size());
        Exception e = assertThrowsExactly(InvalidPositionException.class, ()->{listCat.removeAt(4);});
        assertEquals("Position must be between 1 and 3", e.getMessage());
        assertEquals("Furbaby", listCat.get(3).name);
        assertEquals(3, listCat.size());
    }

    @Test
    void cattestIsEmpty() {
        assertTrue(listCat.isEmpty());
        assertDoesNotThrow(() -> {listCat.add(new Cat("Flounder"));});
        assertFalse(listCat.isEmpty());
    }

    @Test
    void catsetValid1() {
        assertDoesNotThrow(() -> {
            listCat.add(new Cat("Meowy"));
            listCat.add(new Cat("Cathy"));
            listCat.add(new Cat("Furbaby"));
            assertEquals("Cathy", listCat.set(2, new Cat("Flounder")).name);
        });
        assertEquals("Flounder", listCat.get(2).name);
        assertEquals(3, listCat.size());
    }

    @Test
    void catsetInvalid1() {
        assertDoesNotThrow(() -> {
            listCat.add(new Cat("Meowy"));
            listCat.add(new Cat("Cathy"));
            listCat.add(new Cat("Furbaby"));
        });
        Exception e = assertThrowsExactly(InvalidPositionException.class, ()->{listCat.set(4, new Cat("Urie"));});
        assertEquals("Position must be between 1 and 3", e.getMessage());
        assertEquals(3, listCat.size());
    }
}