package si.fri.location_processing.models.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name="locations")
@Table(name="locations")
@NamedQueries(value=
        {
                @NamedQuery(name="Location.findAll",query = "SELECT ev FROM locations ev")
        })
public class LocationEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="address")
    private String address;
    @Column(name="country")
    private String country;
    @Column(name="city")
    private String city;
    @Column(name="street")
    private String street;
    @Column(name = "lat")
    private Float lat;
    @Column(name = "lon")
    private Float lon;

    public LocationEntity(){}

    public LocationEntity(Integer id, String address, String country, String city, String street, Float lat, Float lon) {
        this.id = id;
        this.address = address;
        this.country = country;
        this.city = city;
        this.street = street;
        this.lat = lat;
        this.lon = lon;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public Float getLat() {
        return lat;
    }

    public void setLat(Float lat) {
        this.lat = lat;
    }

    public Float getLon() {
        return lon;
    }

    public void setLon(Float lon) {
        this.lon = lon;
    }
}
