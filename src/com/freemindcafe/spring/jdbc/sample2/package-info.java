/**
 * \brief We need 'context:annotation-config' and 'tx:annotation-driven' annotations.
 * 
 * <p>
 * Without \<context:annotation-config /> and \<tx:annotation-driven transaction-manager="transactionManager" /> 
 * in wiring, \@Transactional does not work.
 * <p>
 * 
 */
package com.freemindcafe.spring.jdbc.sample2;