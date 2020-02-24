package org.password4j;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;


public class BCryptStrategyTest
{




    @Test(expected = BadParametersException.class)
    public void testPBKDF2WrongAlgorithm()
    {
        // GIVEN
        HashingStrategy strategy = new BCryptStrategy(-1);
        String password = "password";

        // WHEN
        strategy.hash(password);

        // THEN
    }




    @Test
    public void testPBKDF2Coherence()
    {
        // GIVEN
        String password = "password";

        // WHEN
        Hash hash = new BCryptStrategy().hash(password);

        // THEN
        Assert.assertTrue(hash.check(password));

    }

    @Test
    public void testPBKDF2CheckWithFixedConfigurations()
    {
        // GIVEN
        String password = "password";

        // WHEN
        Hash hash = new BCryptStrategy(12).hash(password);

        // THEN
        Assert.assertTrue(hash.check(password));
    }


    @Test
    public void testPBKDF2equality()
    {
        // GIVEN
        BCryptStrategy strategy1 = new BCryptStrategy();
        BCryptStrategy strategy2 = new BCryptStrategy();
        BCryptStrategy strategy3 = new BCryptStrategy(15);
        BCryptStrategy strategy4 = new BCryptStrategy(15);
        BCryptStrategy strategy5 = new BCryptStrategy(8);



        // WHEN
        Map<BCryptStrategy, String> map =new HashMap<>();
        map.put(strategy1, strategy1.toString());
        map.put(strategy2, strategy2.toString());
        map.put(strategy3, strategy3.toString());
        map.put(strategy4, strategy4.toString());
        map.put(strategy5, strategy5.toString());



        // THEN
        Assert.assertEquals(3, map.size());
        Assert.assertEquals(strategy1, strategy2);
        Assert.assertEquals(strategy3, strategy4);
    }
}
