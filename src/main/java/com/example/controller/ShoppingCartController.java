package com.example.controller;

import com.example.domain.Item;
import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/exam06")
public class ShoppingCartController {

    @Autowired
    private HttpSession session;

    @Autowired
    private ServletContext application;

    @GetMapping("")
    public String index(Model model) {
        if (application.getAttribute("itemMap") == null) {
            Map<Integer, Item> itemMap = new LinkedHashMap<>();
            Item item1 = new Item();
            item1.setName("手帳ノート");
            item1.setPrice(1000);
            itemMap.put(1, item1);

            Item item2 = new Item();
            item2.setName("文房具セット");
            item2.setPrice(1500);
            itemMap.put(2, item2);

            Item item3 = new Item();
            item3.setName("ファイル");
            item3.setPrice(2000);
            itemMap.put(3, item3);

            application.setAttribute("itemMap", itemMap);
        }

        if (session.getAttribute("cartItemList") == null) {
            List<Item> cartItemList = new LinkedList<>();
            session.setAttribute("cartItemList", cartItemList);
        }

        model.addAttribute("totalPrice", calcTotalPrice());

        return "item-and-cart";
    }

    @PostMapping("inCart")
    public String inCart(String itemNumber) {
        Integer intItemNumber = Integer.parseInt(itemNumber);
        Map<Integer, Item> itemMap = (Map<Integer, Item>) application.getAttribute("itemMap");
        Item item = itemMap.get(intItemNumber);

        List<Item> itemList = (List<Item>) session.getAttribute("cartItemList");
        itemList.add(item);
//        session.setAttribute("cartItemList", itemList);

        return "redirect:/exam06";
    }

    @PostMapping("/delete")
    public String delete(Integer index) {
        List<Item> itemList = (List<Item>) session.getAttribute("cartItemList");
        itemList.remove((int) index);
        session.setAttribute("cartItemList", itemList);

        return "redirect:/exam06";
    }

    public int calcTotalPrice() {
        List<Item> itemList = (List<Item>) session.getAttribute("cartItemList");
        int totalPrice = 0;
        for (Item item : itemList) {
            totalPrice += item.getPrice();
        }
        return totalPrice;
    }
}
