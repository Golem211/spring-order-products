package com.example.demo.service;


import com.example.demo.dao.SupplierRepository;
import com.example.demo.model.Supplier;
import com.example.demo.service.impl.SupplierServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SupplierServiceImplTest {

    private static final int supplierId = 1;

    @InjectMocks
    private SupplierServiceImpl supplierService;

    @Mock
    private SupplierRepository supplierRepository;

    @Captor
    ArgumentCaptor<Integer> integerArgumentCaptor;


    @Test
    void testFindById(){

        Supplier expectedSupplier = createSupplier(supplierId, "DADA");

        when( supplierRepository.findById(eq(supplierId))).thenReturn(Optional.of(expectedSupplier));

        Supplier actualSupplier = supplierService.findById(supplierId);

        verify(supplierRepository).findById(integerArgumentCaptor.capture());

        assertEquals(integerArgumentCaptor.getValue(),supplierId);
        assertNotNull(actualSupplier);
        assertEquals(expectedSupplier.getId(),actualSupplier.getId());
        assertEquals(expectedSupplier.getName(),actualSupplier.getName());

    }

    @Test
    void testFindByIdNull(){
        when( supplierRepository.findById(eq(supplierId))).thenReturn(Optional.empty() );

        Supplier actualSupplier = supplierService.findById(supplierId);

        assertNull(actualSupplier);
    }



    private static Supplier createSupplier(Integer id, String name) {
        Supplier supplier = new Supplier(id,name);
        return supplier;
    }


}
