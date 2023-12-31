package com.laTienda.tienda;

import com.laTienda.producto.Bebida;
import com.laTienda.producto.Envasado;
import com.laTienda.producto.Producto;

public class ItemTienda {
    private Producto storeItem;

    public ItemTienda(Producto storeItem) {
        this.storeItem = storeItem;
    }

    public Producto getStoreItem() {
        return storeItem;
    }

    public void setStoreItem(Producto storeItem) {
        this.storeItem = storeItem;
    }
    public String getName(){ return storeItem.getDescription(); }
    public Float priceForSale(){
        return storeItem.getCustomerPrice();
    }
    public String getIdProduct(){
        return storeItem.getId();
    }
    public Double itemStockPrice(){ return storeItem.productStockCost(); }
    public Boolean isForSale(){ return storeItem.getForSale(); }
    public void itemOutOfStock(){ storeItem.outOfStock(); }
    public void addStockOfItem(Integer quantity){ storeItem.addQuantity(quantity); }
    public void removeStockOfItem(Integer quantity){ storeItem.removeQuantity(quantity); }
    public void activateItem() { storeItem.activateProduct(); }
    public Integer getQuantity(){ return storeItem.getQuantity(); }
    public Float getItemPriceOfSale(){ return storeItem.getCustomerPrice(); }
    public Float getItemPriceOfSale(Float margin){ return this.getItemPriceOfSale() * margin; }
    public Float getItemPriceOfStock(){ return storeItem.getStockPrice(); }
    public Float getMarginOfEarning(){
        double a = getItemPriceOfSale().doubleValue() / getItemPriceOfStock() * 10000;
        Long b = Math.round(a);
        return (b.floatValue() / 10000) - 1;
    }
    //devuelve el margen aplicando descuento por parametro
    public Float getMarginOfEarning(Float discount){
        return ( ( getMarginOfEarning()+1 ) * (1-discount) ) -1;
    }
    public Boolean itemConDescuento(){ return this instanceof ItemTiendaConDescuento; }
    public Boolean itemComestible(){ return this instanceof ItemTiendaComestible; }
    public Boolean itemComestConDesc(){ return this.itemComestible() && this.itemConDescuento();}
    public Boolean itemBebida(){ return storeItem.isBebida(); }
    public Boolean itemEnvasado(){ return storeItem.isEnvasado(); }
    public Boolean itemLimpieza(){ return storeItem.isLimpieza(); }
    public static Boolean idValid(String id) { return !Producto.isOtro(id); }
    public Boolean itemImported(){
        boolean response = false;
        if(storeItem.isEnvasado())
            response = ((Envasado)storeItem).getImported();
        if(storeItem.isBebida())
            response = ((Bebida)storeItem).getImported();
        return response;
    }
    @Override
    public String toString() { return "Item: "+this.storeItem.toString(); }
}
