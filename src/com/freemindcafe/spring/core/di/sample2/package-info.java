/**
 * \brief \<bean abstract="true>
 * Use 'abstract bean' to set the common properties, when the beans are unrelated.
 * 
 * <ul>
 * <li>Abstract=true just indicates that this bean can't be pulled from the container.</li>
 * <li>That just acts a bag for some properties.</li>
 * <li>Other beans can refer to this bean by using the 'parent' property.</li>
 * <li>All the properties from the 'parent' bean for which the corresponding properties exist in this bean are set.<li>
 * </ul>
 * 
 */
package com.freemindcafe.spring.core.di.sample2;