/**
 * \brief \@PreAuthorize can also be applied over class rather than the interface.
 * 
 * <ul>
 * <li>Test cases for user2 -> role2 -> permission2</li>
 * <li>With pre-post-annotations="enabled" attribute, authorization check is enabled. [Test.xml]</li>
 * <li>Spring creates a jdk proxy as the implementation implements one interface.</li>			   
 * <li> If a method calls other method internally, then that call will not be through proxy. [SampleServiceImpl.java -> method6]</li>
 * <li>\@PreAuthorize can also be applied over class rather than the interface.</li>
 * <li> In this case also since SampleServiceImpl implements ISampleService interface, we will have
 *  jdk dynamic proxy. [SampleServiceImpl -> method4]</li>
 * </ul>
 * 
 */
package com.freemindcafe.spring.security.sample3;
