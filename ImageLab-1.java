/*
 * Project 2: Image lab
 *
 * Name: Olivia Terry
 * Email: ort13@pitt.edu
 *
 */
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;


public class ImageLab {

  /*
   * This is the grayscale example. Use it for inspiration / help, but you dont
   * need to change it.
   *
   * Creates and returns a new BufferedImage of the same size as the input
   * image. The new image is the grayscale version of the input (red, green, and
   * blue components averaged together).
   */
  public static BufferedImage imageToGrayscale(BufferedImage input) {
    // store the image width and height in variables for later use
    int img_width = input.getWidth();
    int img_height = input.getHeight();

    // create a new BufferedImage with the same width and height as the input
    // image. TYPE_INT_RGB means we want to specify pixel values using their
    // red, green, and blue components.
    BufferedImage output_img = new BufferedImage(
        img_width, img_height, BufferedImage.TYPE_INT_RGB);

    // Loop over all pixels
    for (int x = 0; x < img_width; x++) {
      for (int y = 0; y < img_height; y++) {

        // Get the color of the input image as a java.awt.Color object
        Color color_at_pos = new Color(input.getRGB(x, y));

        // Call the getRed(), getGreen(), and getBlue() methods on the color
        // object to store the red, green, and blue values into separate integer
        // variables.
        int red = color_at_pos.getRed();
        int green = color_at_pos.getGreen();
        int blue = color_at_pos.getBlue();

        // to get the grayscale version, we just average the red, green, and
        // blue channels, then use that average as the red, green, and blue
        // values in the output image.
        int average = (red + green + blue) / 3;

        // You'll get an IllegalArgumentException if you try to specify red,
        // green, or blue values greater than 255 or less than 0. While it's
        // mathematically impossible to average three values in that range and
        // get a value outside the range, the calculations in the other tasks
        // will sometimes produce values outside the range. This code "clamps"
        // the value to the range 0 - 255
        if (average < 0) {
          average = 0;
        } else if (average > 255) {
          average = 255;
        }

        // make a new Color object, which has the average intensity ((r+g+b)/3)
        // for each of its channels.
        Color average_color = new Color(average, average, average);

        // in the output image, set the color at position (x,y) to our
        // calculated average color
        output_img.setRGB(x, y, average_color.getRGB());
      }
    }

    // return the output image.
    return output_img;
  }

  public static BufferedImage threshold(BufferedImage input, int level) {
    int img_width = input.getWidth();
    int img_height = input.getHeight();
    BufferedImage output_img = new BufferedImage(
        img_width, img_height, BufferedImage.TYPE_INT_RGB);

    //loop over all the pixels
    for (int x = 0; x < img_width; x++) {
      for (int y = 0; y < img_height; y++) {
        //Get color of input image
        Color color_at_pos = new Color(input.getRGB(x, y));
        //Store red, green, and blue values into variables
        int red = color_at_pos.getRed();
        int green = color_at_pos.getGreen();
        int blue = color_at_pos.getBlue();
        int average = (red + green + blue) / 3;

        //Check whether average of red, green, and blue values falls above
        //or below the threshold to decide whether pixel should be black or white
        if (average <= 128) {
          Color average_color = new Color(0,0,0);
          output_img.setRGB(x, y, average_color.getRGB());
        } else if (average > 128) {
          Color average_color = new Color(255,255,255);
          output_img.setRGB(x, y, average_color.getRGB());
        }

      }
    }
    //return output image
    return output_img;
  }


  public static BufferedImage quantize(BufferedImage input) {
    // create new BufferedImage
    int img_width = input.getWidth();
    int img_height = input.getHeight();
    BufferedImage output_img = new BufferedImage(
        img_width, img_height, BufferedImage.TYPE_INT_RGB);

    //Loop over all the pixels
    for (int x = 0; x < img_width; x++) {
      for (int y = 0; y < img_height; y++) {
        //Get color of input image
        Color color_at_pos = new Color(input.getRGB(x, y));
        //Store red, green, and blue values into variables and perform transformation
        int red = (color_at_pos.getRed()/64)*85;
        int green = (color_at_pos.getGreen()/64)*85;
        int blue = (color_at_pos.getBlue()/64)*85;
        //Create new color from transformed red, green, and blue values
        Color average_color = new Color(red,green,blue);
        //Set color at (x,y) to calculated average color
        output_img.setRGB(x, y, average_color.getRGB());
      }
    }
    //return output image
    return output_img;
  }

  public static BufferedImage colorRotate(BufferedImage input) {
    //create new BufferedImage
    int img_width = input.getWidth();
    int img_height = input.getHeight();
    BufferedImage output_img = new BufferedImage(
        img_width, img_height, BufferedImage.TYPE_INT_RGB);

    //Loop over all the pixels
    for (int x = 0; x < img_width; x++) {
      for (int y = 0; y < img_height; y++) {
        //Get color of input image
        Color color_at_pos = new Color(input.getRGB(x, y));
        //Store red, green, and blue values into proper variables for color
        //rotation transformation
        int red = (color_at_pos.getBlue());
        int green = (color_at_pos.getRed());
        int blue = (color_at_pos.getGreen());
        //Create new color from rotated red, green, and blue values
        Color average_color = new Color(red,green,blue);
        //Set color at (x,y) to calculated average color
        output_img.setRGB(x, y, average_color.getRGB());
      }
    }
    //return output image
    return output_img;
  }

  public static BufferedImage warhol(BufferedImage input) {
    // Create new BufferedImage with a tripled width
    int img_width = input.getWidth();
    int img_height = input.getHeight();
    BufferedImage output_img = new BufferedImage(
        3*img_width, img_height, BufferedImage.TYPE_INT_RGB);

    //Loop over all the pixels
    for (int x = 0; x < img_width; x++) {
      for (int y = 0; y < img_height; y++) {
        //Get color of first input image
        Color col1 = new Color(input.getRGB(x, y));
        //Store first image's red, blue, and green values into variables
        int red1 = (col1.getRed());
        int green1 = (col1.getGreen());
        int blue1 = (col1.getBlue());
        //Create new color and transform red, green, and blue values to
        //follow Warhol effect design
        Color average_col1 = new Color(red1,(green1+blue1)/2,(green1+blue1)/2);

        //Get color of next image
        Color col2= new Color(input.getRGB(x,y));
        //Store second image's red, blue, and green values into variables
        int red2 = (col2.getRed());
        int green2 = (col2.getGreen());
        int blue2= (col2.getBlue());
        //Create new color and transform red, green, and blue values to
        //follow Warhol effect design
        Color average_col2= new Color((red2+blue2)/2,green2,(red2+blue2)/2);

        //Get color of third image
        Color col3= new Color(input.getRGB(x,y));
        //Store third image's red, blue, and green values into variables
        int red3 = (col3.getRed());
        int green3 = (col3.getGreen());
        int blue3= (col3.getBlue());
        ////Create new color and transform red, green, and blue values to
        //follow Warhol effect design
        Color average_col3= new Color((red3+green3)/2,(red3+green3)/2,blue3);

        //Set color at (x,y) to first calculated image color
        output_img.setRGB(x, y, average_col1.getRGB());
        //Set color at (x + image width, y) to second calculated image color
        output_img.setRGB(x+img_width, y, average_col2.getRGB());
        //Set color at (x + twice the image width) to third calculated image color
        output_img.setRGB(x+2*img_width, y, average_col3.getRGB());
      }
    }
    //return output image
    return output_img;
  }

  public static BufferedImage sharpen(BufferedImage input) {
    //Create new BufferedImage
    int img_width = input.getWidth();
    int img_height = input.getHeight();
    BufferedImage output_img = new BufferedImage(
        img_width, img_height, BufferedImage.TYPE_INT_RGB);

    //Loop over all the pixels
    for (int x = 0; x < img_width; x++) {
      for (int y = 0; y < img_width; y++) {
        //Get color at position (x,y)
        Color col_at_xy = new Color(input.getRGB(x,y));
        //Store red, green, and blue values for (x,y) into variables
        int red_at_xy   = col_at_xy.getRed();
        int green_at_xy = col_at_xy.getGreen();
        int blue_at_xy  = col_at_xy.getBlue();
        //Store transformed red, green, and blue values of (x,y) into
        //new variables for the output image's red, green, and blue values
        int new_r=red_at_xy*5;
        int new_g=green_at_xy*5;
        int new_b=blue_at_xy*5;

        //Initialize color of the pixel to the left of (x,y)
        Color left_col;
        //Left bounds check. Set color to black if out of bounds
        if ((x-1) >= 0) {
          left_col = new Color(input.getRGB(x-1,y));
        }else {
          left_col = new Color(0, 0, 0);
        }
        //Store red, green, and blue values of the leftmost pixel into variables
        int left_red   = left_col.getRed();
        int left_green = left_col.getGreen();
        int left_blue  = left_col.getBlue();
        //Subtract leftmost pixel's red, green, and blue values from the
        //red, green, and blue values at (x,y)
        new_r-=left_red;
        new_g-=left_green;
        new_b-=left_blue;

        //Initialize color of the pixel above (x,y)
        Color top_col;
        //Upper bounds check. Set color to black if out of bounds
        if ((y+1)<img_height){
          top_col= new Color(input.getRGB(x,y+1));
        }else{
          top_col = new Color(0,0,0);
        }
        //Store red, green, and blue values of the uppermost pixel into variables
        int top_red = top_col.getRed();
        int top_green = top_col.getGreen();
        int top_blue = top_col.getBlue();
        //Subtract uppermost pixel's red, green, and blue values from the
        //output image's red, green, and blue values
        new_r-=top_red;
        new_g-=top_green;
        new_b-=top_blue;

        //Initialize color of the pixel to the right of (x,y)
        Color right_col;
        //Right bounds check. Make sure x+1 does not reach further than the
        //input image's width. Set color to black if out of bounds
        if ((x+1)<img_width){
          right_col = new Color(input.getRGB(x+1,y));
        }else {
          right_col = new Color(0,0,0);
        }
        //Store red, green, and blue values of the rightmost pixel into variables
        int right_red   = right_col.getRed();
        int right_green = right_col.getGreen();
        int right_blue  = right_col.getBlue();
        //Subtract rightmost pixel's red, green, and blue values from the
        //output image's red, green, and blue values
        new_r-=right_red;
        new_g-=right_green;
        new_b-=right_blue;


        //Initialize color of pixel below (x,y)
        Color bottom_col;
        //Lower bounds check. Set color to black if out of bounds
        if ((y-1)>=0){
          bottom_col = new Color(input.getRGB(x,y-1));
        }else{
          bottom_col = new Color(0,0,0);
        }
        //Store red, green, and blue values of bottom pixel into variables
        int bottom_red = bottom_col.getRed();
        int bottom_green = bottom_col.getGreen();
        int bottom_blue = bottom_col.getBlue();
        //Subtract bottom pixel's red, green, and blue values from the output
        //image's red, green, and blue values
        new_r-=bottom_red;
        new_g-=bottom_green;
        new_b-=bottom_blue;

        //Ensure that new red, green, and blue values of output image are
        //between 0 and 255. If less than 0, set to 0. If greater than 255, set
        //to 255.
        if (new_r<0){
          new_r=0;
        }else if (new_r>255){
          new_r=255;
        }

        if (new_g<0){
          new_g=0;
        }else if (new_g>255){
          new_g=255;
        }

        if (new_b<0){
          new_b=0;
        }else if (new_b>255){
          new_b=255;
        }

        //Create new color using newly calculated red, green, and blue values
        Color out_color = new Color(new_r, new_g, new_b);
        //Set color at (x,y) to newly created color (sharpened effect)
        output_img.setRGB(x, y, out_color.getRGB());
    }
  }
  //return output image
  return output_img;
}

  public static BufferedImage boxBlur(BufferedImage input) {
    //create new BufferedImage
    int img_width = input.getWidth();
    int img_height = input.getHeight();
    BufferedImage output_img = new BufferedImage(
        img_width, img_height, BufferedImage.TYPE_INT_RGB);

    //Loop over all the pixels.
    for (int x = 0; x < img_width; x++) {
      for (int y = 0; y < img_width; y++) {
        //Get color at position (x,y)
        Color col_at_xy = new Color(input.getRGB(x,y));
        //Store red, green, and blue values at (x,y) into variables
        int red_at_xy   = col_at_xy.getRed();
        int green_at_xy = col_at_xy.getGreen();
        int blue_at_xy  = col_at_xy.getBlue();

        //Create new variables for output image's red, green, and blue values
        int new_r=red_at_xy;
        int new_g=green_at_xy;
        int new_b=blue_at_xy;

        //Left bounds check. If in bounds, get the colors of all pixels in the
        //leftmost column. If out of bounds, set pixel colors to black.
        if ((x-1) >= 0) {
          //Get left pixel's color
          Color left_col = new Color(input.getRGB(x-1,y));
          //Store left pixel's red, green, and blue values into variables
          int left_red   = left_col.getRed();
          int left_green = left_col.getGreen();
          int left_blue  = left_col.getBlue();
          //Add the left pixel's red, green, and blue values into those of the
          //output image.
          new_r+=left_red;
          new_g+=left_green;
          new_b+=left_blue;

          if ((y+1)<img_width){
            //Get upper left pixel's color
            Color left_top_col = new Color(input.getRGB(x-1,y+1));
            //Store upper left pixel's red, green, and blue values into those of
            //the output image
            int left_top_red   = left_top_col.getRed();
            int left_top_green = left_top_col.getGreen();
            int left_top_blue  = left_top_col.getBlue();
            //Add the upper left pixel's red, green, and blue values into those of
            //the output image
            new_r+=left_top_red;
            new_g+=left_top_green;
            new_b+=left_top_blue;
          }else{
            Color left_top_col = new Color(0, 0, 0);
          }

          if ((y-1)>=0){
            //Get lower left pixel's color
            Color left_bottom_col = new Color(input.getRGB(x-1,y-1));
            //Store lower left pixel's red, green, and blue values into those of
            //the output image
            int left_bottom_red   = left_bottom_col.getRed();
            int left_bottom_green = left_bottom_col.getGreen();
            int left_bottom_blue  = left_bottom_col.getBlue();
            //Add the lower left pixel's red green and blue values into those of
            //the output image
            new_r+=left_bottom_red;
            new_g+=left_bottom_green;
            new_b+=left_bottom_blue;
          }else{
            Color left_bottom_col = new Color(0, 0, 0);
          }
        }else {
          //Set out of bounds pixels to black
          Color left_col = new Color(0, 0, 0);
          Color left_top_col = new Color(0, 0, 0);
          Color left_bottom_col = new Color(0, 0, 0);
        }

        //Upper bounds check. If in bounds, get color of uppermost pixel.
        //If out of bounds, set color to black.
        if ((y+1)<img_height){
          //Get color of top pixel.
          Color top_col= new Color(input.getRGB(x,y+1));
          //Store red, green, and blue values of top pixel into variables.
          int top_red = top_col.getRed();
          int top_green = top_col.getGreen();
          int top_blue = top_col.getBlue();
          //Add top pixel's red, green, and blue values into those of the
          //output image
          new_r+=top_red;
          new_g+=top_green;
          new_b+=top_blue;
        }else{
          //If out of bounds, set pixel to black
          Color top_col = new Color(0,0,0);
        }

        //Right bounds check. Ensure that the image's width is not exceeded.
        if ((x+1)<img_width){
          //Get color of rightmost pixel
          Color right_col = new Color(input.getRGB(x+1,y));
          //Store red, green, and blue values of rightmost pixel into variables
          int right_red   = right_col.getRed();
          int right_green = right_col.getGreen();
          int right_blue  = right_col.getBlue();
          //Add red, green, and blue values of rightmost pixel into those of
          //the output image
          new_r+=right_red;
          new_g+=right_green;
          new_b+=right_blue;

          //Right bounds check
          if ((y+1)<img_height){
            //Get color of upper right pixel
            Color right_top_col = new Color(input.getRGB(x+1,y+1));
            //Store red, green, and blue values of upper right pixel into variables
            int right_top_red   = right_top_col.getRed();
            int right_top_green = right_top_col.getGreen();
            int right_top_blue  = right_top_col.getBlue();
            //Add red, green and blue values of upper right pixel into those of
            //the output image
            new_r+=right_top_red;
            new_g+=right_top_green;
            new_b+=right_top_blue;
          }else{
            Color right_top_col = new Color(0, 0, 0);
          }

          //Left bounds check
          if ((y-1)>=0){
            //Get color of lower right pixel
            Color right_bottom_col = new Color(input.getRGB(x+1,y-1));
            //Store red, green, and blue values of lower right pixel into variables
            int right_bottom_red   = right_bottom_col.getRed();
            int right_bottom_green = right_bottom_col.getGreen();
            int right_bottom_blue  = right_bottom_col.getBlue();
            //Add red, green, and blue values of lower right pixel into those of
            //the output image
            new_r+=right_bottom_red;
            new_g+=right_bottom_green;
            new_b+=right_bottom_blue;
          }else{
            Color right_bottom_col = new Color(0, 0, 0);
          }


        }else {
          //If out of bounds, set pixel colors to black
          Color right_col = new Color(0,0,0);
          Color right_top_col = new Color(0, 0, 0);
          Color right_bottom_col = new Color(0, 0, 0);
        }

        //Lower bounds check. If in bounds, get color of bottom pixel. If out
        //out of bounds, set pixel color to black.
        if ((y-1)>=0){
          //Get color of bottom pixel
          Color bottom_col = new Color(input.getRGB(x,y-1));
          //Store red, green, and blue values of bottom pixel into variables
          int bottom_red = bottom_col.getRed();
          int bottom_green = bottom_col.getGreen();
          int bottom_blue = bottom_col.getBlue();
          //Add red, green, and blue values of bottom pixel into those of the
          //output image
          new_r+=bottom_red;
          new_g+=bottom_green;
          new_b+=bottom_blue;
        }else{
          //If out of bounds, set pixel color to black
          Color bottom_col = new Color(0,0,0);
        }
    //Divide newly created red, green, and blue values by 9 for blur effect
    new_r /= 9;
    new_g /= 9;
    new_b /= 9;

        //Check to ensure new red, green, and blue values are between 0 and 255.
        //If a value is less than 0, set it to zero. If greater than 255, set it
        //to 255.
        if (new_r<0){
          new_r=0;
        }else if (new_r>255){
          new_r=255;
        }

        if (new_g<0){
          new_g=0;
        }else if (new_g>255){
          new_g=255;
        }

        if (new_b<0){
          new_b=0;
        }else if (new_b>255){
          new_b=255;
        }
        //Create new color using newly found red, green, and blue values
        Color out_color = new Color(new_r, new_g, new_b);
        //Set color at (x,y) to newly created color (box blur effect)
        output_img.setRGB(x, y, out_color.getRGB());
      }
    }
  //return output image
  return output_img;
  }

  public static BufferedImage simpleEdgeDetect(BufferedImage input) {
    // Replace this method body with your code
    return null;
  }

  public static BufferedImage sobelEdgeDetect(BufferedImage input) {
    // Replace this method body with your code
    return null;
  }


  /*=============== You don't need to change anything below this line =======*/

  public static BufferedImage readImage(String filename) {
    BufferedImage img = null;
    try {
      img = ImageIO.read(new File(filename));
    } catch (IOException e) {
      System.out.println("Error - couldn't load " + filename);
      return null;
    }

    return img;
  }

  public static void writeImage(BufferedImage img, String filename) {
		try {
      System.out.printf("Writing %s...\n", filename);
      File ImageFile = new File(filename);
			ImageIO.write(img, "jpg", ImageFile);
		} catch (IOException e) {
      System.out.printf("Error writing %s\n", filename);
			e.printStackTrace();
		}
  }

  public static void main(String[] args) {
    if (args.length == 0) {
      System.out.println("Usage: java ImageLab <filename>");
      System.exit(0);
    }

    BufferedImage img = readImage(args[0]);

    if(img == null) {
      System.out.println("Couldn't read image");
      System.exit(0);
    }

    System.out.println("Running imageToGrayscale()...");
    BufferedImage gray = imageToGrayscale(img);
    if (gray != null) {
      writeImage(gray, "grayscale.jpg");
    } else {
      System.out.println("Got null");
    }

    System.out.println("Running threshold()...");
    BufferedImage thresh = threshold(img, 128);
    if (thresh != null) {
      writeImage(thresh, "threshold.jpg");
    } else {
      System.out.println("Got null");
    }

    System.out.println("Running quantize()...");
    BufferedImage quant = quantize(img);
    if (quant != null) {
      writeImage(quant, "quant.jpg");
    } else {
      System.out.println("Got null");
    }

    System.out.println("Running colorRotate()...");
    BufferedImage rot = colorRotate(img);
    if (rot != null) {
      writeImage(rot, "colorrotate.jpg");
    } else {
      System.out.println("Got null");
    }

    System.out.println("Running warhol()...");
    BufferedImage war = warhol(img);
    if (war != null) {
      writeImage(war, "warhol.jpg");
    } else {
      System.out.println("Got null");
    }

    System.out.println("Running sharpen()...");
    BufferedImage sharp = sharpen(img);
    if (sharp != null) {
      writeImage(sharp, "sharpen.jpg");
    } else {
      System.out.println("Got null");
    }

    System.out.println("Running boxBlur()...");
    BufferedImage blur = boxBlur(img);
    if (blur != null) {
      writeImage(blur, "blur.jpg");
    } else {
      System.out.println("Got null");
    }

    System.out.println("Running simpleEdgeDetect()...");
    BufferedImage edge = simpleEdgeDetect(img);
    if (edge != null) {
      writeImage(edge, "edge.jpg");
    } else {
      System.out.println("Got null");
    }

    System.out.println("Running sobelEdgeDetect()...");
    BufferedImage sobel = sobelEdgeDetect(img);
    if (sobel != null) {
      writeImage(sobel, "sobel.jpg");
    } else {
      System.out.println("Got null");
    }

  }

}
