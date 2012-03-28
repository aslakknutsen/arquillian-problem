package de.reschif.arquillian.problem.component1;

import javax.ejb.Remote;
import javax.ejb.Stateless;

import de.reschif.arquillian.problem.api.IMyService;

// @Singleton
// @Startup
@Stateless
@Remote(IMyService.class)
public class MyServiceOne implements IMyService {

  public MyServiceOne() {
    System.out.println("MyServiceOne: New instance created");
  }

  @Override
  public boolean doSomething() {
    System.out.println("MyServiceOne#doSomething");
    return true;
  }

}
