package com.tjgwebservices.tjgxmlcms.controller.shop;

import static com.tjgwebservices.tjgxmlcms.controller.consulting.ConsultingController.retrieveConsultantPage;
import com.tjgwebservices.tjgxmlcms.dbo.shop.CartStatusDBO;
import com.tjgwebservices.tjgxmlcms.dbo.shop.ShopOrderStatusDBO;
import com.tjgwebservices.tjgxmlcms.dbo.shop.ShopPaymentTypeDBO;
import com.tjgwebservices.tjgxmlcms.dbo.shop.ShopProductDBO;
import com.tjgwebservices.tjgxmlcms.form.shop.CartStatusForm;
import com.tjgwebservices.tjgxmlcms.form.shop.ShopOrderStatusForm;
import com.tjgwebservices.tjgxmlcms.form.shop.ShopPaymentTypeForm;
import com.tjgwebservices.tjgxmlcms.form.shop.ShopProductForm;
import com.tjgwebservices.tjgxmlcms.model.Consulting;
import com.tjgwebservices.tjgxmlcms.model.shop.CartStatus;
import com.tjgwebservices.tjgxmlcms.model.shop.ShopOrderStatus;
import com.tjgwebservices.tjgxmlcms.model.shop.ShopPaymentType;
import com.tjgwebservices.tjgxmlcms.model.shop.ShopProduct;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.servlet.ServletContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ShopController {

    private static List<CartStatus> cartStatuses = new ArrayList<CartStatus>();
    private static List<ShopOrderStatus> shopOrderStatuses = new ArrayList<ShopOrderStatus>();
    private static List<ShopPaymentType> shopPaymentTypes = new ArrayList<ShopPaymentType>();
    private static List<ShopProduct> shopProducts = new ArrayList<ShopProduct>();
    
    static {
    }
    
    @Autowired
    private ServletContext context;


    @RequestMapping(value = { "/shop/shop" }, method = RequestMethod.GET)
    public String shopMainPage(Model model) {
        String xmlPath = context.getRealPath("") +
                "xml" + File.separator + "consulting.xml";

        Consulting consulting = retrieveConsultantPage(xmlPath);
        model.addAttribute("consulting", consulting);
         
        return "shop/shop";
    }

    @RequestMapping(value = { "/shop/productList" }, method = RequestMethod.GET)
    public String reviewList(Model model) {
        shopProducts = ShopProductDBO.loadShopProducts();
        model.addAttribute("shopProducts", shopProducts);
 
        return "shop/productList";
    }
    
    @RequestMapping(value = { "/shop/addCartStatus" }, method = RequestMethod.GET)
    public String addCartStatusForm(Model model) {
 
        CartStatusForm cartStatusForm = new CartStatusForm();
        model.addAttribute("cartStatusForm", cartStatusForm);
 
        return "/shop/addCartStatus";
    }
 
    @RequestMapping(value = { "/shop/addCartStatus" }, method = RequestMethod.POST)
    public String addCartStatusSave(Model model, //
        @ModelAttribute("cartStatusForm") CartStatusForm cartStatusForm) {
        String description = cartStatusForm.getDescription();
         if (description != null && description.length() > 0){
            CartStatus cartStatus = new CartStatus(description);
            cartStatuses.add(cartStatus);
            CartStatusDBO.saveSQLCartStatus(cartStatus);
            return "redirect:/shop/shop";
        }
        String error = "All fieds are required!";
        model.addAttribute("errorMessage", error);
        return "/shop/addCartStatus";
    }

    @RequestMapping(value = { "/shop/editCartStatus/{id}" }, method = RequestMethod.GET)
    public String editCartStatusForm(Model model,
            @PathVariable("id") Integer id) {
 
        CartStatusForm cartStatusForm = new CartStatusForm();
        model.addAttribute("cartStatusForm", cartStatusForm);
        CartStatusForm cartStatusEditForm = new CartStatusForm();
                List<CartStatus> editCartStatuses = cartStatuses.stream()
            .filter((cartStatus) -> cartStatus.getId() == id)
            .collect(Collectors.toList());
        if (editCartStatuses.size()==1) { 
        CartStatus editCartStatus = editCartStatuses.get(0);
        cartStatusEditForm.setDescription(editCartStatus.getDescription());
        cartStatusEditForm.setId(id);
        model.addAttribute("cartStatusEditForm", cartStatusEditForm);
 
        return "shop/editCartStatus";
        } else {
            model.addAttribute("errorMessage","Cart Status id not found");
            return "shop/editCartStatus/"+id;            
            
        }
    }
 
    @RequestMapping(value = { "shop/editCartStatus" }, method = RequestMethod.POST)
    public String editCartStatusSave(Model model, //
        @ModelAttribute("cartStatusForm") CartStatusForm cartStatusForm) {
        Integer id = cartStatusForm.getId();
        String description = cartStatusForm.getDescription();
         if (description != null && description.length() > 0){
            CartStatus cartStatus = new CartStatus(description);
            cartStatus.setId(id);
            CartStatusDBO.updateCartStatus(cartStatus);
            return "redirect:/shop/shop";
        }
        String error = "All fieds are required!";
        model.addAttribute("errorMessage", error);
        return "/shop/addCartStatus";
    }


    @RequestMapping(value = { "/shop/addShopOrderStatus" }, method = RequestMethod.GET)
    public String addShopOrderStatusForm(Model model) {
 
        ShopOrderStatusForm shopOrderStatusForm = new ShopOrderStatusForm();
        model.addAttribute("shopOrderStatusForm", shopOrderStatusForm);
 
        return "/shop/addShopOrderStatus";
    }
 
    @RequestMapping(value = { "/shop/addShopOrderStatus" }, method = RequestMethod.POST)
    public String addShopOrderStatusSave(Model model, //
        @ModelAttribute("shopOrderStatusForm") ShopOrderStatusForm shopOrderStatusForm) {
        String description = shopOrderStatusForm.getDescription();
         if (description != null && description.length() > 0){
            ShopOrderStatus shopOrderStatus = new ShopOrderStatus(description);
            shopOrderStatuses.add(shopOrderStatus);
            ShopOrderStatusDBO.saveSQLShopOrderStatus(shopOrderStatus);
            return "redirect:shop/shop";
        }
        String error = "All fieds are required!";
        model.addAttribute("errorMessage", error);
        return "/shop/addShopOrderStatus";
    }

    @RequestMapping(value = { "/shop/editShopOrderStatus/{id}" }, method = RequestMethod.GET)
    public String editShopOrderForm(Model model,
            @PathVariable("id") Integer id) {
 
        ShopOrderStatusForm shopOrderStatusForm = new ShopOrderStatusForm();
        model.addAttribute("shopOrderStatusForm", shopOrderStatusForm);
        ShopOrderStatusForm shopOrderStatusEditForm = new ShopOrderStatusForm();
                List<ShopOrderStatus> editShopOrderStatuses = shopOrderStatuses.stream()
            .filter((shopOrderStatus) -> shopOrderStatus.getId() == id)
            .collect(Collectors.toList());
        if (editShopOrderStatuses.size()==1) { 
        ShopOrderStatus editShopOrderStatus = editShopOrderStatuses.get(0);
        shopOrderStatusEditForm.setDescription(editShopOrderStatus.getDescription());
        shopOrderStatusEditForm.setId(id);
        model.addAttribute("shopOrderStatusEditForm", shopOrderStatusEditForm);
 
        return "shop/editShopOrderStatus";
        } else {
            model.addAttribute("errorMessage","Shop Order Status id not found");
            return "shop/shop";            
            
        }
    }
 
    @RequestMapping(value = { "shop/editShopOrderStatus" }, method = RequestMethod.POST)
    public String editShopOrderStatusSave(Model model, //
        @ModelAttribute("shopOrderStatusForm") ShopOrderStatusForm shopOrderStatusForm) {
        Integer id = shopOrderStatusForm.getId();
        String description = shopOrderStatusForm.getDescription();
         if (description != null && description.length() > 0){
            ShopOrderStatus shopOrderStatus = new ShopOrderStatus(description);
            shopOrderStatus.setId(id);
            ShopOrderStatusDBO.updateShopOrderStatus(shopOrderStatus);
            return "redirect:/shop/shop";
        }
        String error = "All fieds are required!";
        model.addAttribute("errorMessage", error);
        return "/shop/addShopOrderStatus";
    }


    @RequestMapping(value = { "/shop/addShopPaymentType" }, method = RequestMethod.GET)
    public String addShopPaymentTypeForm(Model model) {
 
        ShopPaymentTypeForm shopPaymentTypeForm = new ShopPaymentTypeForm();
        model.addAttribute("shopPaymentTypeForm", shopPaymentTypeForm);
 
        return "/shop/addShopPaymentType";
    }
 
    @RequestMapping(value = { "/shop/addShopPaymentType" }, method = RequestMethod.POST)
    public String addShopPaymentTypeSave(Model model, //
        @ModelAttribute("shopPaymentTypeForm") ShopPaymentTypeForm shopPaymentTypeForm) {
        String paymentTypeDescription = shopPaymentTypeForm.getPaymentTypeDescription();
         if (paymentTypeDescription != null && paymentTypeDescription.length() > 0){
            ShopPaymentType shopPaymentType = new ShopPaymentType(paymentTypeDescription);
            shopPaymentTypes.add(shopPaymentType);
            ShopPaymentTypeDBO.saveSQLShopPaymentType(shopPaymentType);
            return "redirect:/shop/shop";
        }
        String error = "All fieds are required!";
        model.addAttribute("errorMessage", error);
        return "/shop/addShopPaymentType";
    }

    @RequestMapping(value = { "/shop/editShopPaymentType/{id}" }, method = RequestMethod.GET)
    public String editShopPaymentTypeForm(Model model,
            @PathVariable("id") Integer id) {
 
        ShopPaymentTypeForm shopPaymentTypeForm = new ShopPaymentTypeForm();
        model.addAttribute("shopPaymentTypeForm", shopPaymentTypeForm);
        ShopPaymentTypeForm shopPaymentTypeEditForm = new ShopPaymentTypeForm();
                List<ShopPaymentType> editShopPaymentTypees = shopPaymentTypes.stream()
            .filter((shopPaymentType) -> shopPaymentType.getId() == id)
            .collect(Collectors.toList());
        if (editShopPaymentTypees.size()==1) { 
        ShopPaymentType editShopPaymentType = editShopPaymentTypees.get(0);
        shopPaymentTypeEditForm.setPaymentTypeDescription(editShopPaymentType.getPaymentTypeDescription());
        shopPaymentTypeEditForm.setId(id);
        model.addAttribute("shopPaymentTypeEditForm", shopPaymentTypeEditForm);
 
        return "shop/editShopPaymentType";
        } else {
            model.addAttribute("errorMessage","Shop Payment Type id not found");
            return "shop/shop";            
            
        }
    }
 
    @RequestMapping(value = { "shop/editShopPaymentType" }, method = RequestMethod.POST)
    public String editShopPaymentTypeSave(Model model, //
        @ModelAttribute("shopPaymentTypeForm") ShopPaymentTypeForm shopPaymentTypeForm) {
        Integer id = shopPaymentTypeForm.getId();
        String paymentTypeDescription = shopPaymentTypeForm.getPaymentTypeDescription();
         if (paymentTypeDescription != null && paymentTypeDescription.length() > 0){
            ShopPaymentType shopPaymentType = new ShopPaymentType(paymentTypeDescription);
            shopPaymentType.setId(id);
            ShopPaymentTypeDBO.updateShopPaymentType(shopPaymentType);
            return "redirect:/shop/shop";
        }
        String error = "All fieds are required!";
        model.addAttribute("errorMessage", error);
        return "/shop/addShopPaymentType";
    }

    @RequestMapping(value = { "/shop/addShopProduct" }, method = RequestMethod.GET)
    public String addShopProductForm(Model model) {
 
        ShopProductForm shopProductForm = new ShopProductForm();
        model.addAttribute("shopProductForm", shopProductForm);
 
        return "/shop/addShopProduct";
    }
 
    @RequestMapping(value = { "/shop/addShopProduct" }, method = RequestMethod.POST)
    public String addShopProductSave(Model model, //
        @ModelAttribute("shopProductForm") ShopProductForm shopProductForm) {
        String description = shopProductForm.getDescription();
        Float price = shopProductForm.getPrice();
         if (description != null && description.length() > 0 &&
                 price != null && price >= 0){
            ShopProduct shopProduct = new ShopProduct(description,price);
            shopProducts.add(shopProduct);
            ShopProductDBO.saveSQLShopProduct(shopProduct);
            return "redirect:/shop/shop";
        }
        String error = "All fieds are required!";
        model.addAttribute("errorMessage", error);
        return "/shop/addShopProduct";
    }

    @RequestMapping(value = { "/shop/editShopProduct/{id}" }, method = RequestMethod.GET)
    public String editShopProductForm(Model model,
            @PathVariable("id") Integer id) {
 
        ShopProductForm shopProductForm = new ShopProductForm();
        model.addAttribute("shopProductForm", shopProductForm);
        ShopProductForm shopProductEditForm = new ShopProductForm();
                List<ShopProduct> editShopProductes = shopProducts.stream()
            .filter((shopProduct) -> shopProduct.getId() == id)
            .collect(Collectors.toList());
        if (editShopProductes.size()==1) { 
        ShopProduct editShopProduct = editShopProductes.get(0);
        shopProductEditForm.setDescription(editShopProduct.getDescription());
        shopProductEditForm.setPrice(editShopProduct.getPrice());
        shopProductEditForm.setId(id);
        model.addAttribute("shopProductEditForm", shopProductEditForm);
 
        return "/shop/editShopProduct";
        } else {
            model.addAttribute("errorMessage","Shop Product id not found");
            return "/shop/shop";            
            
        }
    }
 
    @RequestMapping(value = { "shop/editShopProduct" }, method = RequestMethod.POST)
    public String editShopProductSave(Model model, //
        @ModelAttribute("shopProductForm") ShopProductForm shopProductForm) {
        Integer id = shopProductForm.getId();
        String description = shopProductForm.getDescription();
        Float price = shopProductForm.getPrice();
         if (description != null && description.length() > 0 &&
             price != null && price>= 0){
            ShopProduct shopProduct = new ShopProduct(description, price);
            shopProduct.setId(id);
            ShopProductDBO.updateShopProduct(shopProduct);
            return "redirect:/shop/shop";
        }
        String error = "All fieds are required!";
        model.addAttribute("errorMessage", error);
        return "/shop/addShopProduct";
    }


    
}
