/**
 * \brief SampleServiceImpl does not implement ISampleService interface then cglib proxy is created.
 * 
 * <ul>
 * <li>If we move every expression based control to the class and SampleServiceImpl does not implement ISampleService interface, 
 * then cglib proxy is created and authorization checks behavior remains the same. [SampleServiceImpl and Test]</li>
 * </ul>
 * 
 */
package com.freemindcafe.spring.security.sample4;
