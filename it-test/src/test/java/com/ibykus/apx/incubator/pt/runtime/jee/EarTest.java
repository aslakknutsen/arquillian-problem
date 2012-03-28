package com.ibykus.apx.incubator.pt.runtime.jee;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import javax.ejb.EJB;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.spec.EnterpriseArchive;
import org.jboss.shrinkwrap.resolver.api.DependencyResolvers;
import org.jboss.shrinkwrap.resolver.api.maven.MavenDependencyResolver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import de.reschif.arquillian.problem.api.IMyService;

@RunWith(Arquillian.class)
public class EarTest {

  @EJB(mappedName = "java:global/test/MyServiceOne!de.reschif.arquillian.problem.api.IMyService")
  public IMyService myService;

  @Deployment
  public static EnterpriseArchive getUserManager() {
    return DependencyResolvers
        .use(MavenDependencyResolver.class)
        .artifact(
            "de.reschif.arquillian-problem:arquillian-problem-component1-ear:ear:0.0.1-SNAPSHOT")
        .resolveAs(EnterpriseArchive.class).iterator().next();
  }

  @Before
  public void init() {
    System.out.println("EarTest: ++++++++++++++++++++++++");
  }

  @After
  public void close() {
    System.out.println("EarTest: ------------------------");
  }

  @Test
  public void injectEJBFromEar() throws Exception {
    System.out.println("EarTest: ........................");
    assertNotNull("no Bean injected for myService", myService);
    assertTrue("Error on calling the bean", myService.doSomething());
  }

}
