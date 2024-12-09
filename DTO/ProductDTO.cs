using System.ComponentModel.DataAnnotations;

namespace ANI.DTO;

public abstract class ProductBaseDTO
{
    [Required]
    public string Name { get; set; } = null!;
    public string? Description { get; set; }
    [Required]
    public decimal Price { get; set; }
    [Required]
    [Range(1, int.MaxValue, ErrorMessage = "Stock must be more than 0")]
    public int Stock { get; set; }
    [Required]
    public Guid UserID { get; set; }
}

public class ProductResponseDTO : ProductBaseDTO
{
    public Guid ProductID { get; set; }
    public string ProductPictureUrl { get; set; } = null!;
}

public class ProductCreateDTO : ProductBaseDTO
{
    [Required]
    public IFormFile ProductPictureUrl { get; set; } = null!;
}

public class ProductUpdateDTO : ProductBaseDTO
{
    public Guid ProductID { get; set; }
    public IFormFile? ProductPictureUrl { get; set; } = null!;
}

public class ProductSecDTO : ProductBaseDTO
{
    public string ProductPictureUrl { get; set; } = null!;
}