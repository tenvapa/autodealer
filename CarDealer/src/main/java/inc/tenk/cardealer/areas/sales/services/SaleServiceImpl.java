package inc.tenk.cardealer.areas.sales.services;

import org.modelmapper.ModelMapper;
import inc.tenk.cardealer.areas.sales.repositories.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SaleServiceImpl implements SaleService {
    private SaleRepository saleRepository;
    private ModelMapper mapper;

    @Autowired
    public SaleServiceImpl(SaleRepository saleRepository) {
        this.saleRepository = saleRepository;
        this.mapper = new ModelMapper();
    }

//    @Override
//    public List<SaleDTO> getAllSalesInfo() {
//        return this.saleRepository.getAllSalesInfo();
//    }

//    @Override
//    public SaleDTO getSaleById(Long id) {
//        return this.saleRepository.getSaleById(id);
//    }
//
//    @Override
//    public List<SaleDTO> getDiscountedSales() {
//        return this.saleRepository.getDiscountedSales();
//    }
//
////    @Override
////    public List<SaleDTO> getDiscountedSalesByPercent(double discountPercent) {
////
////        return this.saleRepository.getDiscountedSalesByPercent(discountPercent/100);
////    }
//
//    @Override
//    public void addSale(SaleReviewDTO saleReviewDto) {
//        Sale sale = mapper.map(saleReviewDto,Sale.class);
//        sale.setDiscount(sale.getDiscount()/100);
//        sale.getCar().setSale(sale);
//        Set<Sale> customerSales = sale.getUser().getSales();
//        customerSales.add(sale);
//        this.saleRepository.save(sale);
//    }
}
