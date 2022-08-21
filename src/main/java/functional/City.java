package functional;

import java.util.Objects;

public class City {

    String name;
    double latitude;
    double longitude;

    // Constructor
    public City( String name, double latitude, double longitude ) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
    }
    // end of Constructor

    // Getter & Setter

    public String getName() {
        return name;
    }

    public void setName( String name ) {
        this.name = name;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude( double latitude ) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude( double longitude ) {
        this.longitude = longitude;
    }
    // end of Getter & Setter

    @Override
    public String toString() {
        return "City:" +
                "name='" + name + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude;
    }

    @Override
    public boolean equals( Object o ) {
        if ( this == o )
            return true;
        if ( o == null || getClass() != o.getClass() )
            return false;

        City city = ( City ) o;

        if ( Double.compare( city.latitude, latitude ) != 0 )
            return false;
        if ( Double.compare( city.longitude, longitude ) != 0 )
            return false;
        return Objects.equals( name, city.name );
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = name != null ? name.hashCode() : 0;
        temp = Double.doubleToLongBits( latitude );
        result = 31 * result + ( int ) ( temp ^ ( temp >>> 32 ) );
        temp = Double.doubleToLongBits( longitude );
        result = 31 * result + ( int ) ( temp ^ ( temp >>> 32 ) );
        return result;
    }
    
} // end of City
