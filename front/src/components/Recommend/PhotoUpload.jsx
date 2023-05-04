/* createObjectURL, revokeObjectURL을 사용
    동기적 실행, 주어진 객체를 가리키는 URL을 DOMString으로 변환
    창을 닫을 때까지 유지됨. 이전에 해제하기 위해서는 메모리 누수 방지 위해서 revokeObjectURL()을 호출해야함.
    FileLeader와 달리 시간이 필요하지 않고 revoke만 잘해주면 속도가 많이 빠르다고함
    FileLeader와 readAsDataURL을 사용하는 방법과의 차이점에 대해 공부할 것 
    블로그와 유트브를 참고해여
    */
import S from "./styled";

const PhotoUpload = ({
  setSelectedFile,
  setImagePreview,
  imagePreview,
  width = "100px",
  height = "100px",
}) => {
  const handleImageChange = (e) => {
    const file = e.target.files[0];
    if (file) {
      const imageUrl = URL.createObjectURL(file);
      setSelectedFile(file);
      setImagePreview(imageUrl);
    }
  };

  const handleImageRemove = () => {
    URL.revokeObjectURL(imagePreview);
    setSelectedFile(null);
    setImagePreview(null);
  };

  return (
    <S.ImageBox>
      {imagePreview ? (
        <div>
          <img src={imagePreview} alt="preview" style={{ width, height }} />
          <button onClick={handleImageRemove}>Remove</button>
        </div>
      ) : (
        <div>
          <input type="file" accept="image/*" onChange={handleImageChange} />
          <p>이미지 파일을 업로드해주세요.</p>
        </div>
      )}
    </S.ImageBox>
  );
};

export default PhotoUpload;
