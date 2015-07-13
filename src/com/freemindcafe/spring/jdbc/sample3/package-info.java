/**
 * \brief Absence of \@Transactional results in no transaction. Presence of \@Transactional results in transaction support.
 * 
 * <p>
 * We have \<context:annotation-config /> and \<tx:annotation-driven transaction-manager="transactionManager" /> in wiring.
 * <p>
 * <ul>
 * <li>Methods that don't have \@Transactional either at interface or implementation are not transactional.</li>
 * <li>Methods that have \@Transactional either at interface or implementation are transactional.</li>
 * </ul>
 */
package com.freemindcafe.spring.jdbc.sample3;