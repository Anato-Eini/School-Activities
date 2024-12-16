﻿using System.ComponentModel.DataAnnotations;
using Microsoft.EntityFrameworkCore.ChangeTracking.Internal;

namespace ANI.DTO;

/// <summary>
/// Data Transfer Object for Rating.
/// </summary>
public class RatingDTO
{
    /// <summary>
    /// Gets or sets the content of the rating.
    /// </summary>
    public string? Content { get; set; }

    /// <summary>
    /// Gets or sets the numbkjer of stars given in the rating.
    /// </summary>
    [Range(1, 5)]
    [Required]
    public int Stars { get; set; }

    /// <summary>
    /// Gets or sets the unique identifier for the product being rated.
    /// </summary>
    [Required]
    public Guid ProductID { get; set; }

    /// <summary>
    /// Gets or sets the unique identifier for the user who gave the rating.
    /// </summary>
    [Required]
    public Guid UserID { get; set; }
}

public class RatingCreateDTO : RatingDTO
{
    /// <summary>
    /// Gets or sets the URL of the image associated with the rating.
    /// </summary>
    public IFormFile? ImageUrl { get; set; }

}

public class RatingResponseDTO : RatingDTO
{

    /// <summary>
    /// Gets or sets the unique identifier for the rating.
    /// </summary>
    [Required]
    public Guid RatingID { get; set; }
    /// <summary>
    /// Gets or sets the URL of the image associated with the rating.
    /// </summary>
    public string? ImageUrl { get; set; }
}

/// <summary>
/// Data Transfer Object for fetching ratings by product.
/// </summary>
public class RatingFetchDTO
{
    public Guid RatingID { get; set; }
    public string Username { get; set; } = null!;
    public int Stars { get; set; }
    public string? Content { get; set; }
    public string? ImageUrl { get; set; }
}