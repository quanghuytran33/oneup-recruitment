package com.oneup.refactor;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class OrderUtils {

  /*
  The method should return interface List instead of its implementation (LinkedList)
  Method getAllOrders and findOrdersForProduct must be typed with class Order instead of generic type
  Parameter 'debug' is useless
  Use lambda syntax
  Use method contains(element) of List instead of parsing the whole list with loop
  Variable 'l' can be directly LinkedList instead of creating a wrapper

  NullPointerException risk
  if (order.getProducts().size() > 0)

  Must use equals method here because this code compare only object references, not it contents
  Furthermore, it should break products loop after this line. If not, the result will be incorrect
  found = (p2 == p);

  Useless null check
  if (found && order != null)
  l.add(order);

  List must get by index get(i) instead of get(O)
  Order order = (Order) list.get(0);

  public LinkedList findOrdersForProduct(Product p, boolean debug) {
    ArrayList l = new ArrayList();
    ArrayList list = getAllOrders();
    for (int i = 0; i < list.size(); i++) {
      Order order = (Order) list.get(0);
      boolean found = false;
      if (order.getProducts().size() > 0) {
        for (int j = 0; j <= order.getProducts().size(); j++) {
          Product p2 = order.getProducts().get(j);
          found = (p2 == p);
        }
        if (found && order != null)
          l.add(order);
      }
    }
    return new LinkedList(l);
  }
  */

  public List<Order> findOrdersForProduct(Product p) {
    return getAllOrders().stream()
        .filter(order -> order.getProducts().contains(p))
        .collect(Collectors.toList());
  }

  public List<Order> getAllOrders() {
    return Collections.emptyList();
  }

}
