package com.ibykus.apx.incubator.pt.runtime.jee;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import javax.ejb.EJB;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import de.reschif.arquillian.problem.api.IMyService;
import de.reschif.arquillian.problem.component1.MyServiceOne;

@RunWith(Arquillian.class)
public class JarTest {

  @EJB(mappedName = "java:global/test/MyServiceOne!de.reschif.arquillian.problem.api.IMyService")
  public IMyService myService;

  @Deployment
  public static JavaArchive createTestArchive() {
    return ShrinkWrap.create(JavaArchive.class).addClasses(IMyService.class, MyServiceOne.class);
  }

  @Before
  public void init() {
    System.out.println("JarTest: ++++++++++++++++++++++++");
  }

  @After
  public void close() {
    System.out.println("JarTest: ------------------------");
  }

  @Test
  public void injectEJBFromJar() throws Exception {
    System.out.println("JarTest: ........................");
    assertNotNull("no Bean injected for myService", myService);
    assertTrue("Error on calling the bean", myService.doSomething());
  }

}
