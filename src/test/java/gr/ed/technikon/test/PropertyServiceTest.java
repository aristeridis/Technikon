package gr.ed.technikon.test;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import gr.ed.technikon.Repositories.OwnerRepositoryInterface;
import gr.ed.technikon.Repositories.PropertyRepository;
import gr.ed.technikon.Repositories.PropertyRepositoryInterface;
import gr.ed.technikon.enums.PropertyType;
import gr.ed.technikon.models.Owner;
import gr.ed.technikon.models.Property;
import gr.ed.technikon.services.AdminService;
import gr.ed.technikon.services.OwnerService;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;

public class PropertyServiceTest {

	@Mock
	private PropertyRepositoryInterface propertyRepositoryInterface;

	@InjectMocks
	private PropertyRepository propertyRepository;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
	}
	@Test
	public void findById(Long id){
		
		
		
		
		
		
//		List<Property> p=new ArrayList<>();
//		Owner ownerTest=new Owner(12l,1345673l,"Kostas","Polemis","Platonos 4","23456","kostas@test.gr","kostas","1234kostas",p);
//		OwnerService owner=new OwnerService(propertyRepository);
//        Property property = new Property(178223l, "Korai 25", 1992,PropertyType.MAISONETTE,ownerTest);
//	p.add(property);
//
//       when(propertyRepository.findByIds(178223l)).thenReturn(property);
//               	Property property1Property = propertyRepository.findByIds(178223l);

//        String result = property.getAddress();
//        assertEquals(1992, property1Property.getYearOfConstruction());
//        assertEquals("1", result.getId());
//        assertEquals("Korai 25",propertyAddress);
//        assertEquals(178223l, propertyId);

        //verify(propertyRepository, times(1)).findById("1");
}
}