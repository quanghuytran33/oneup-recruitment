package com.oneup.refactor;

import java.util.List;
import lombok.Data;

@Data
public class Order {

  private List<Product> products;
}
