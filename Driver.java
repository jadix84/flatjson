import java.io.*;
import java.util.Vector;
public class Driver
{
    public static void main(String...args)throws IOException
    {
         Vector<String> vec = new Vector<String>();
         
        BufferedReader file = new BufferedReader(new FileReader("data.txt"));
        String line = file.readLine();
        System.out.println(line);
        line = file.readLine();
        int level=0;
         String result="";
         int countBrackets=0;
        while(line!= null)
        {
            
            
            String[] arr = line.split(":");
            if(arr.length >1)
            {
                String key  =arr[0].trim();             
                String val = arr[1].trim();
               
                char c = val.charAt(0);
                
                if(Character.isDigit(c))
                {
                  if(vec.size()==0)   
                   result+=key+": "+c +",\n";
                  else
                  {
                      String v="\"";
                      for(int t=0;t<vec.size()-1;t++)
                      {
                        String b =vec.get(t);
                        b =b.substring(1,b.length()-1)+".";
                        v+=b;
                    }
                      
                      
                      key = key.substring(1,key.length());
                      result+=v+key+": "+c +",\n"; 
                    }
                }
                
                   
               
               
                else if(c =='{')
                {
                    if(vec.size()>0)
                        vec.removeElementAt(vec.size()-1);
                    vec.add(key);
                    countBrackets++;
               //      System.out.println("left bracket :" +  countBrackets);
                    line = file.readLine();
                   
                    while(!line.trim().equals("}"))
                    {
                        arr = line.split(":");
                        if(arr.length > 1)
                        {
                             String nkey  =arr[0].trim();
                              vec.add(nkey);
                            
                             String nval = arr[1].trim();
               
                             c = nval.charAt(0);
                             if(c == '{')
                             {
                                 countBrackets++;
                  
                              }
                            
                             if(Character.isDigit(c))
                            {
                                 
                               
                                
                                if(vec.size()== 2)
                                {
                                
                                    for(int i=0;i<vec.size()-1;i++)
                                    {
                                        result+=vec.get(i).substring(0,vec.get(i).length()-1)+".";
                                    }
                                    result+=vec.get(vec.size()-1).substring(1,vec.get(vec.size()-1).length())+": "+ c+",\n";
                                
                                }
                                else
                                {
                                    result+=vec.get(0).substring(0,vec.get(0).length()-1)+".";
                                    for(int i=1;i<vec.size()-1;i++)
                                    {
                                        result+=vec.get(i).substring(1,vec.get(i).length()-1)+".";
                                    }
                                    result+=vec.get(vec.size()-1).substring(1,vec.get(vec.size()-1).length())+": "+ c+",\n";
                                }
                                
                                vec.remove(nkey);
                                
                                
                               
                            }
                            
                      
                           
                        }
                       
                        line = file.readLine();
                       
                    }
                  
               
                  
                    
                }
                
                
             
            }
                   
        //   vec.clear();
            line = file.readLine();
            if(line != null)
            {
                if(line.trim().equals("}"))
                {
                     countBrackets--;
                     for(int t=vec.size()-1;t>=countBrackets;t--)
                        vec.removeElementAt(t);
                     // System.out.println("----------------->"+countBrackets);
                     // System.out.println();
                    // for(int x=0;x<vec.size();x++)
                        // System.out.println(vec.get(x) + " ");
                }
            }
        }
        System.out.println(result.substring(0,result.length()-2));
        System.out.print("}");
        file.close();
    }
}
