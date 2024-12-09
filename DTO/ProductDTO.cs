namespace ANI.DTO;

public class ProductResponseDTO
{
    public Guid ProductID { get; set; }
    public string Name { get; set; } = null!;
    public string? Description { get; set; }
    public decimal Price { get; set; }
    public int Stock { get; set; }
    public string ProductPictureUrl { get; set; } = null!;
    public Guid UserID { get; set; }
}

public class ProductUpdateDTO
{
    public Guid ProductID { get; set; }
    public string Name { get; set; } = null!;
    public string? Description { get; set; }
    public decimal Price { get; set; }
    public int Stock { get; set; }
    public IFormFile? ProductPictureUrl { get; set; } = null!;
    public Guid UserID { get; set; }
}