/**
 * \brief Forcing cglib proxy and in that case authorizations checks that have been defined on the class  will be applicable.
 * 
 * <ul>
 * <li>Forcing cglib proxy and in that case authorizations checks that have been defined on the class will be applicable.</li>
 * <li>Also spring will create the proxy class of type SampleServiceImpl and not that of ISampleService.</li>
 * <li> So the type for setter injection should be SampleServiceImpl and not ISampleService.</li>
 * </ul>
 * 
 */
package com.freemindcafe.spring.security.sample5;
