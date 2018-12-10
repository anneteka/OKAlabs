import java.util.Iterator;

public class ST <Key extends Comparable<Key>, Value>
{

    Element[] elements;
    int elementsLength;
    int numOfFillingElement =0;
    private ST()
    {
        elements = (Element[]) new Object[12];;
        elementsLength = elements.length;
    }
   void put(Key key, Value val)
   {

       for(int i=0;i<elements.length;i++)
       {
           if(elements[i].getKey()==null)
           {
               elements[i] = new Element(key,val);
               return;
           }
           if(key.compareTo((Key)elements[i].getKey())>0) continue;
           if(key.compareTo((Key)elements[i].getKey()) == 0)
           {
               elements[i] = new Element(key,val);
               return;
           }
           if (numOfFillingElement+1==elementsLength) resize();
           freeSpace(i);
           elements[i] = new Element(key,val);
           numOfFillingElement++;
       }
   }



    private void resize()
    {
        Element[] temp = (Element[]) new Object[elementsLength];
        for (int i=0;i<elementsLength;i++) {
            temp[i] = elements[i];
        }
        elementsLength = elementsLength+elementsLength/4;
        elements = (Element[]) new Object[elementsLength];
        for (int i=0;i<elementsLength;i++) {
           elements[i] =  temp[i];
        }
    }

    private void freeSpace(int place)
    {
        Element[] temp = (Element[]) new Object[elementsLength-place];
        for(int i=place;i<elementsLength;i++)
        {
            temp[i] = elements[i];
        }
        for(int i=place+1;i<elementsLength+1;i++)
        {
            elements[i] = temp[i];
        }

    }
     Value get(Key key)
     {
         for(int i=0;i<numOfFillingElement;i++)
         {
             if(key.compareTo((Key)elements[i].getKey())==0) return (Value)elements[i].getValue();
         }
         return null;
     }
    void delete(Key key)
    {
        if (!contains(key)) return;
        for(int i=0;i<numOfFillingElement;i++)
        {
            if(key.compareTo((Key)elements[i].getKey())==0)
            {
                elements[i].value = null;
                elements[i].key = null;
                numOfFillingElement--;
            }

        }
    }
    public boolean contains(Key key){
        return get(key) != null;
    }
    boolean isEmpty()
    {
        return numOfFillingElement==0;
    }
    int size()
    {
        return numOfFillingElement;
    }
    Key min()
    {
        if (isEmpty()) return null;
        return (Key) elements[0].getKey();
    }
    Key max()
    {
        if (isEmpty()) return null;
        return (Key) elements[numOfFillingElement+1].getKey();
    }
    Key floor(Key key)
    {
        if(isEmpty()) return null;
        if(contains(key)) return key;
        for(int i=0;i<numOfFillingElement;i++)
        {
            if (key.compareTo((Key)elements[i].getKey())<0)
            {
                if(i>0) return (Key)elements[i-1].getKey();
                else return (Key)elements[i].getKey();
            }
        }
        return null;
    }
    Key ceiling(Key key)
    {
        if(isEmpty()) return null;
        if(contains(key)) return key;
        for(int i=0;i<numOfFillingElement;i++)
        {
            if (key.compareTo((Key)elements[i].getKey())<0)
            {
                return (Key)elements[i].getKey();
            }
        }
        return null;
    }

    int rank(Key key)
    {
        int count = 0;
        for(int i=0;i<numOfFillingElement;i++)
        {
            if(key.compareTo((Key)elements[i].getKey())>0) return count;
            count++;
        }
        return count;
    }


    Key select(int k)
    {
        if (isEmpty()) return null;
        return (Key)elements[k].getKey();
    }
    void deleteMin()
    {
        if(isEmpty()) return;
        numOfFillingElement--;
        Element[] temp = (Element[]) new Object[numOfFillingElement];
        for(int i=0;i<numOfFillingElement-1;i++)
        {
            temp[i] = elements[i+1];
        }
        for(int i=0;i<numOfFillingElement;i++)
        {
            elements[i] = temp[i];
        }
    }
    void deleteMax()
    {
        if(isEmpty()) return;
        numOfFillingElement--;
        elements[numOfFillingElement+1] = new Element(null,null);
    }
    int size(Key lo, Key hi)
    {
        int count =0;
        for(int i=0;i<numOfFillingElement;i++)
        {
            if(lo.compareTo((Key)elements[i].getKey())>0&hi.compareTo((Key)elements[i].getKey())<0) count++;
        }
        return count;
    }

    Iterable<Key> keys()
    {
        Iterable it = new Iterable() {
            @Override
            public Iterator iterator() {
                Key.i
            }
        }
    }

    Iterable<Key> keys(Key lo, Key hi)



    private class Element
    {
        Key key;
        Value value;
        Element(Key key, Value value)
        {
            this.key = key;
            this.value = value;
        }

        private Key getKey() {
            return key;
        }

        private Value getValue() {
            return value;
        }
    }
}

