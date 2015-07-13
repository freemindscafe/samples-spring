/**
 * \brief Absence of \@Transactional results in no transaction. Presence of \@Transactional results in transaction support.
 * 
 * <p>
 * We have \<context:annotation-config /> and \<tx:annotation-driven transaction-manager="transactionManager" /> in wiring.
 * </p>
 * <ul>
 * <li>If the target class implements one or more interfaces, then Spring will create a JDK dynamic proxy that implements every interface.
 * IncorrectProxiesWillFailTheTest.java is for that.</li>
 * <li>If the target class implements no interfaces, Spring will use CGLIB to create a new class on the fly that is a subclass ("extends") 
 * the target class.SampleServiceImplWorksAsItDoesNotImplementInterfaceTest id for that.</li>
 * <li>One can force spring to use cglib proxy SampleServiceImplWorksAsItUsesCglibProxyTest is for that.</li>
 * </ul>
 * 
 * <h3>Summary</h3>
 * <ul>
 * <li>Always specify \@Transactional on classes and let spring decide to make a jdk or cglib proxy.</li>
 * <li>If the class implements an interface then we will have jdk proxy else spring will create cglib proxy.
 * So we should have cglig in the classpath always.</li>
 * <li>If we have the annotation on the class, both the proxies will recognize the annotations.
 * cglib proxy will ignore the annotations defined on interface. see below for more details.
 * SampleServiceImplWorksAsItUsesCglibProxyTest.service3_Is_Transactional_At_Interface_But_Cglib_Ignores_Annotation_On_Interface_Hence_On_Exception_Data_Is_Persisted
 * </li>
 * <li>In no case we should wire based on the implementation type and not on interface type.</li>
 * <li>If we wire using implementation type then only cglib will work. And to force spring to use cglib irrespective of classes implementing interfaces,
 * we will have to use the following in xml configuration proxy-target-class="true". see below for more details.
 * SampleServiceImplWorksAsItUsesCglibProxyTest</li>
 * 
 */
package com.freemindcafe.spring.jdbc.sample4;