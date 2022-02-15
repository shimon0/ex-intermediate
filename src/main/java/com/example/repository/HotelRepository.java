package com.example.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.domain.Hotel;

@Repository
public class HotelRepository {
	public static final RowMapper<Hotel> HOTEL_ROW_WAPPER=(rs,i) ->{
		Hotel hotel=new Hotel();
		hotel.setHotelName(rs.getString("hotel_name"));
		hotel.setAddress(rs.getString("address"));
		hotel.setAreaName(rs.getString("area_name"));
		hotel.setId(rs.getInt("id"));
		hotel.setNearestStation(rs.getString("nearest_station"));
		hotel.setPrice(rs.getInt("price"));
		hotel.setParking(rs.getString("parking"));
		
		return hotel;
	};
	@Autowired
	private NamedParameterJdbcTemplate template;
	
	public List<Hotel> findHotel(Integer price) {
		String sql = "SELECT * FROM hotels WHERE price<= :price AND price>=0";
		if(price==null) {
			String sql2="SELECT * FROM hotels";
			sql=sql2;
		}
		List<Hotel> hotelList=template.query(sql, HOTEL_ROW_WAPPER);
		return hotelList;
	}
}
