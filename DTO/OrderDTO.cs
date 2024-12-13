namespace ANI.DTO;

/* public class OrderDTO
{
    public Guid OrderID { get; set; }
    public int Quantity { get; set; }
    public Guid ProductID { get; set; }
    public Guid UserID { get; set; }
} */

public class OrderCreateDTO
{
    public int Quantity { get; set; }
    public Guid ProductID { get; set; }
    public Guid UserID { get; set; }
}

public class OrderResponseDTO
{
    public Guid OrderID { get; set; }
    public int Quantity { get; set; }
    public Guid ProductID { get; set; }
    public Guid UserID { get; set; }
    public DateTime CreatedAt { get; set; }
}

public class OrderUpdateDTO
{
    public Guid OrderID { get; set; }
    public int Quantity { get; set; }
}