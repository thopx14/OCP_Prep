package generics.aufgaben;

import java.util.List;

interface Inter { }
class X {
    Inter i = null;
}
class Base implements Inter { }
class Derived extends Base { }
class C extends Derived { }
class A extends Derived { }
class AD extends A { }
class ADD extends AD { }

 

public class Generics_Platzhalter {

    @SuppressWarnings({ "unused", "rawtypes", "unchecked" })
    public static void main( String[] args ) {
        
        List list = null;
        List<?> listUnknown = null;//? = ? extends Object
        List<Object> listObject = null;
        List<? super Object> listSuperObject = null;
        List<? extends Object> listExtendsObject = null;
        List<Inter> listInter = null;
        List<? super Inter> listSuperInter = null; // <- Inter, Object
        List<? extends Inter> listExtendsInter = null;
        List<Base> listBase = null; // Base
        List<? super Base> listSuperBase = null;
        List<? extends Base> listExtendsBase = null; // Base, Derived, A, AD, ADD, C
        List<Derived> listDerived = null; // <- Derived
        List<? super Derived> listSuperDerived = null;
        List<? extends Derived> listExtendsDerived = null; // Derived, A, AD, ADD, C
        List<A> listA = null;
        List<? super A> listSuperA = null; // <- A, Derived, Base, Inter, Object
        List<? extends A> listExtendsA = null;
        List<C> listC = null;
        List<? super C> listSuperC = null; // <- C, Derived, Base, Inter, Object
        List<? extends C> listExtendsC = null;
        List<AD> listAD = null;
        List<? super AD> listSuperAD = null;
        List<? extends AD> listExtendsAD = null; // <- AD, ADD
        List<ADD> listADD = null; // ADD
        List<? super ADD> listSuperADD = null;
        List<? extends ADD> listExtendsADD = null; // <- ADD
        
         listSuperA  = listSuperInter;              // Okay!
//         listDerived = listSuperC;                // cf!
         listExtendsADD  = listADD;                 // Okay!
         listExtendsBase = listExtendsADD;          // Okay!
//         listAD  = listBase;                      // cf!
//         listADD = listSuperA;                    // cf!
         listExtendsBase = listExtendsBase;         // Okay!
//         listBase    = listExtendsADD;            // cf!
//         listObject  = listExtendsDerived;        // cf!
//         listSuperA  = listExtendsInter;          // cf!
//         listSuperObject = listAD;                // cf!
//         listSuperC = listExtendsAD;              // cf!
         listExtendsAD = listExtendsADD;            // Okay!
//         listDerived = listADD;                   // cf!
//         listADD = listA;                         // cf!
//         listBase = listA;                        // cf!
         listSuperBase = listInter;                 // Okay!
//         listSuperBase = listUnknown;             // cf!
         listExtendsObject = listA;                 // Okay!
//         listExtendsC = listSuperC;               // cf!
         listUnknown = listExtendsAD;               // Okay!
//         listSuperDerived = listA;                // cf!
         listSuperDerived = listInter;              // Okay!
//         listDerived = listAD;                    // cf!
//         listExtendsInter = listSuperBase;        // cf!
         listExtendsBase = list;                    // Okay! Nicht sicher, aber okay!
         listExtendsObject = listExtendsC;          // Okay!
//         listAD = listObject;                     // cf!
//         listExtendsC = listUnknown;              // cf!
//         listSuperC = listA;                      // cf!
         listExtendsInter = listExtendsBase;        // Okay!
         listExtendsObject = listSuperDerived;      // Okay!
         listExtendsObject = listExtendsADD;        // Okay!
//         listSuperDerived = listExtendsC;         // cf!
//         listAD = listSuperObject;                // cf!
         listSuperAD = listSuperDerived;            // Okay!
//         listExtendsAD = listA;                   // cf!
         listUnknown = listSuperC;                  // Okay!
//         listExtendsADD = listC;                  // cf!
//         listAD = listSuperAD;                    // cf!
//         listSuperBase = listExtendsAD;           // cf!
//         listC = listSuperA;                      // cf!
//         listA = listInter;                       // cf!
         list = listSuperAD;                        // Okay!
//         listObject = listExtendsInter;           // cf!
         listSuperA = listSuperA;                   // Okay!
//         listExtendsAD = listExtendsC;            // cf!
         list = listADD;                            // Okay!
//         listA = listDerived;                     // cf!
         listUnknown = listSuperA;                  // Okay!

    }

}
